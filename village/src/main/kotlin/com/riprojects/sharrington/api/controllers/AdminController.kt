package com.riprojects.sharrington.api.controllers

import com.riprojects.sharrington.api.mappers.toLandmarkDto
import com.riprojects.sharrington.api.mappers.toLandmarkImageDto
import com.riprojects.sharrington.api.mappers.toPointOfInterestDto
import com.riprojects.sharrington.api.mappers.toPointOfInterestImageDto
import com.riprojects.sharrington.dto.LandmarkDto
import com.riprojects.sharrington.dto.LandmarkImageDto
import com.riprojects.sharrington.dto.PoiImageUrlDto
import com.riprojects.sharrington.dto.PointOfInterestDto
import com.riprojects.sharrington.dto.PointOfInterestImageDto
import com.riprojects.sharrington.dto.RecordLandmarkRequest
import com.riprojects.sharrington.dto.RecordPoiRequest
import com.riprojects.sharrington.dto.UpdateLandmarkRequest
import com.riprojects.sharrington.dto.UpdatePoiRequest
import com.riprojects.sharrington.infra.storage.SupabaseStorageService
import com.riprojects.sharrington.service.AdminService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID


@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val adminService: AdminService,
    private val storageService: SupabaseStorageService
)  {

    @PostMapping("/landmarks/recordLandmark")
    fun recordLandmark(
        @Valid @RequestBody body: RecordLandmarkRequest
    ): LandmarkDto {
        println("Record Landmark")
        println("Received $body")
        return adminService.recordLandmark(
            name = body.name,
            description = body.description,
            latitude = body.latitude,
            longitude = body.longitude
        ).toLandmarkDto()
    }

    @GetMapping("/landmarks/{landmarkId}")
    fun getLandmark(
        @PathVariable("landmarkId") landmarkId: UUID
    ): LandmarkDto {
        return adminService.getLandmarkById(landmarkId).toLandmarkDto()
    }

    @GetMapping("/landmarks")
    fun getAllLandmarks(): List<LandmarkDto> {
        return adminService.getAllLandmarks().map { it.toLandmarkDto() }
    }

    // delete landmark and all associated POI & landmarkImages (not storage files)
   @DeleteMapping("/landmarks/{landmarkId}")
   fun deleteLandmark(
       @PathVariable("landmarkId") landmarkId: UUID
   ) {
       adminService.deleteLandmark(landmarkId)
   }

    @PutMapping("landmarks/{landmarkId}")
    fun updateLandmark(
        @PathVariable("landmarkId") landmarkId: UUID,
        @Valid @RequestBody body: UpdateLandmarkRequest
    ): LandmarkDto {
        return adminService.updateLandmark(landmarkId, body).toLandmarkDto()
    }



    /*
      Point Of Interest
     */

//    @PostMapping("/landmarks/recordPoi/{landmarkId}")
//    fun recordPoi(
//        @PathVariable("landmarkId") landmarkId: UUID,
//        @Valid @RequestBody body: RecordPoiRequest
//    ): PointOfInterestDto {
//        println("Record Poi")
//        println("Received $body")
//        return adminService.recordPoi(
//            id = landmarkId,
//            name = body.name,
//            description = body.description,
//        ).toPointOfInterestDto()
//    }

    @GetMapping("/landmarks/poi/{landmarkId}")
    fun getPointOfInterest(
        @PathVariable("landmarkId") landmarkId: UUID
    ): PointOfInterestDto {
        return adminService.getPoiByLandmarkId(landmarkId).toPointOfInterestDto()
    }

    @GetMapping("/landmarks/poi/")
    fun getAllPois(): List<PointOfInterestDto> {
        return adminService.getAllPois().map { it.toPointOfInterestDto() }
    }

    @DeleteMapping("/landmarks/poi/{poiId}")
    fun deletePoi(
        @PathVariable("poiId") poiId: UUID
    ) {
        adminService.deletePoi(poiId)
    }

    @PutMapping("landmarks/poi/{poiId}")
    fun updatePoi(
        @PathVariable("poiId") poiId: UUID,
        @Valid @RequestBody body: UpdatePoiRequest
    ): PointOfInterestDto {
        return adminService.updatePoi(poiId, body).toPointOfInterestDto()
    }



    /*
        LandmarkImage
     */


    @PostMapping("/landmarks/{landmarkId}/images")
    fun uploadLandmarkImage(
        @PathVariable ("landmarkId") landmarkId: UUID,
        @RequestParam("file") file: MultipartFile,
        @RequestParam("caption") caption: String?
    ): LandmarkImageDto { //dto
        val imagePath = storageService.uploadImage("images", landmarkId, file)
        println("admin controller upload imagePath: $imagePath")
       // val url = storageService.getPublicUrl(imagePath)
        //println("admin controller upload url: $url")
        return adminService.attachImageToLandmark(landmarkId, imagePath, caption).toLandmarkImageDto()
    }

    @GetMapping("/landmarks/{landmarkId}/images")
    fun getLandmarkImages(
        @PathVariable landmarkId: UUID
    ): List<LandmarkImageDto> {

        return adminService
            .getImagesForLandmark(landmarkId)
            .map { it.toLandmarkImageDto() }
    }

    // deletes landmark image & image in Supabase bucket, NOT landmark
    @DeleteMapping("/landmark-images/{landmarkImageId}")
    fun deleteLandmarkImage(
        @PathVariable("landmarkImageId") landmarkImageId: UUID
    ) {
        val imagePath = adminService.getLandmarkImagePath(landmarkImageId)
        val publicUrl = storageService.getPublicUrl(imagePath)
        println("public url $publicUrl")
        storageService.deleteFile(publicUrl)
        adminService.deleteLandmarkImage(landmarkImageId)
    }

    /*
        Point Of Interest Image
     */

    @PostMapping("/landmarks/poi/{poiId}/images")
    fun uploadPoiImage(
        @PathVariable ("poiId") poiId: UUID,
        @RequestParam("file") file: MultipartFile,
        @RequestParam ("caption") caption: String?,
    ): PointOfInterestImageDto { //dto
        val imagePath = storageService.uploadImage("images", poiId, file)
        println("adminController path $imagePath")
        val url = storageService.getPublicUrl(imagePath)
        println("adminController url $url")
        return adminService.attachImageToPoi(poiId, imagePath,caption).toPointOfInterestImageDto()
    }

    @GetMapping("/landmarks/poi/{poiImageId}/images")
    fun getPoiImagePublicUrl(
        @PathVariable ("poiImageId") poiImageId: UUID
    ): PoiImageUrlDto {
        val imagePath = adminService.getPoiImagePath(poiImageId)

        return PoiImageUrlDto(
            url = storageService.getPublicUrl(imagePath)
        )

//        val publicUrl = storageService.getPublicUrl(imagePath)
//        println(publicUrl)
    }

    // deletes poi image & image in Supabase bucket, NOT main POI row in table
    @DeleteMapping("/landmarks/poi/{poiImageId}/images")
    fun deletePoiImage(
        @PathVariable("poiImageId") poiImageId: UUID
    ) {
        println("delete poi image triggered")
        val imagePath = adminService.getPoiImagePath(poiImageId)
        val publicUrl = storageService.getPublicUrl(imagePath)
        println("public url $publicUrl")
        storageService.deleteFile(publicUrl)
        adminService.deletePoiImage(poiImageId)
    }
}