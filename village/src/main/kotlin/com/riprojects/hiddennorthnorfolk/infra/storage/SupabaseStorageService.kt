package com.riprojects.hiddennorthnorfolk.infra.storage

import com.riprojects.hiddennorthnorfolk.domain.exception.StorageException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.multipart.MultipartFile
import java.util.UUID


@Service
class SupabaseStorageService(
    @param:Value("\${supabase.url}") private val supabaseUrl: String,
    @param:Value("\${supabase.service-key}") private val serviceKey: String,
    @param:Value("\${supabase.bucket}") private val bucket: String,
    private val supabaseRestClient: RestClient,
) {
    companion object {
        private val allowedMimeTypes = mapOf(
            "image/jpeg" to "jpg",
            "image/jpg" to "jpg",
            "image/png" to "png",
            "image/webp" to "webp",
        )
    }

    fun
            uploadImage(folder: String, id: UUID, file: MultipartFile): String {
        println("SERVICE KEY = $serviceKey")
        val mimeType = file.contentType
            ?: throw IllegalArgumentException("File must have a content type")

        val extension = allowedMimeTypes[mimeType]
            ?: throw IllegalArgumentException("Unsupported file type: $mimeType")

        val fileName = "${UUID.randomUUID()}.$extension"
        val path = "$folder/$id/$fileName"

        val response = supabaseRestClient.post()
            .uri("$supabaseUrl/storage/v1/object/$path")
            .header("apikey", serviceKey)
            .header("Authorization", "Bearer $serviceKey")
            .header("Content-Type", mimeType)
            .body(file.bytes)
            .retrieve()
            .toEntity(String::class.java)

        if (!response.statusCode.is2xxSuccessful) {
            throw RuntimeException("Failed to upload image: ${response.body}")
        }

        return path // storage path only
    }

    fun getPublicUrl(path: String): String =
        "$supabaseUrl/storage/v1/object/public/$path"


    // get public urls for multiple images in supabase storage
    fun getPublicUrls(paths: List<String>): List<String> {
        return paths.map { path ->
            "$supabaseUrl/storage/v1/object/public/$path"
            }
        }

    // single image file delete
    fun deleteFile(url: String) {
        println("storageService delete file url $url")
        val path = if(url.contains("/object/public/")) {
            url.substringAfter("/object/public/")
        } else throw StorageException("Invalid file URL format")

        println("delete file path $path")
        val deleteUrl = "/storage/v1/object/$path"
        println("storageService delete file delete url ${deleteUrl}")

        val response = supabaseRestClient
            .delete()
            .uri(deleteUrl)
            .retrieve()
            .toBodilessEntity()

        if(response.statusCode.isError) {
            throw StorageException("Unable to delete file: ${response.statusCode.value()}")
        }
    }
}