package com.riprojects.sharrington.service

import com.riprojects.sharrington.domain.exception.LandmarkNotFoundException
import com.riprojects.sharrington.domain.exception.PointOfInterestNotFoundException
import com.riprojects.sharrington.domain.model.Landmark
import com.riprojects.sharrington.domain.model.LandmarkImage
import com.riprojects.sharrington.domain.model.PointOfInterest
import com.riprojects.sharrington.domain.model.PointOfInterestImage
import com.riprojects.sharrington.dto.UpdateLandmarkRequest
import com.riprojects.sharrington.dto.UpdatePoiRequest
import com.riprojects.sharrington.infra.database.entities.LandmarkEntity
import com.riprojects.sharrington.infra.database.entities.LandmarkImageEntity
import com.riprojects.sharrington.infra.database.entities.PointOfInterestEntity
import com.riprojects.sharrington.infra.database.entities.PointOfInterestImageEntity
import com.riprojects.sharrington.infra.database.mappers.toLandmark
import com.riprojects.sharrington.infra.database.mappers.toLandmarkImage
import com.riprojects.sharrington.infra.database.mappers.toPoiImage
import com.riprojects.sharrington.infra.database.mappers.toPointOfInterest
import com.riprojects.sharrington.infra.database.repositories.LandmarkImageRepository
import com.riprojects.sharrington.infra.database.repositories.LandmarkRepository
import com.riprojects.sharrington.infra.database.repositories.PointOfInterestImageRepository
import com.riprojects.sharrington.infra.database.repositories.PointOfInterestRepository
import com.riprojects.sharrington.infra.storage.SupabaseStorageService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

//how to prevent duplication of landmarks with multiple upload attempts????

@Service
class AdminService(
    private val landmarkRepository: LandmarkRepository,
    private val pointOfInterestRepository: PointOfInterestRepository,
    private val pointOfInterestImageRepository: PointOfInterestImageRepository,
    private val landmarkImageRepository: LandmarkImageRepository,
    private val storageService: SupabaseStorageService
) {

    @Transactional
    fun recordLandmark(name: String, description: String, latitude: Double, longitude: Double): Landmark {
        val trimmedName = name.trim()
        val trimmedDescription = description.trim()

        val savedLandmark = landmarkRepository.saveAndFlush(
            LandmarkEntity(
                name = trimmedName,
                description = trimmedDescription,
                latitude = latitude,
                longitude = longitude
            )
        )
        pointOfInterestRepository.saveAndFlush(
            PointOfInterestEntity(
                landmark = savedLandmark,
                name = savedLandmark.name,
                description = savedLandmark.description
            )
        )

        return savedLandmark.toLandmark()
    }

    fun getLandmarkById(
        id: UUID
    ): Landmark {
        val landmark =
            landmarkRepository.findByIdOrNull(id)
                ?: throw LandmarkNotFoundException("Landmark with $id not found")

        return landmark.toLandmark()
    }

    fun getAllLandmarks(): List<Landmark> {
        val landmarks = landmarkRepository.findAll()

        return landmarks.map { it.toLandmark() }.sortedByDescending { it.updatedAt }
    }

    @Transactional
    fun deleteLandmark(id: UUID) {
        landmarkRepository.deleteById(id)
    }

    @Transactional
    fun updateLandmark(id: UUID, body: UpdateLandmarkRequest): Landmark {
        val landmark =
            landmarkRepository.findByIdOrNull(id)
                ?: throw LandmarkNotFoundException("Landmark with $id not found")

            body.name?.let {landmark.name =  it}
            body.description?.let {landmark.description =  it}
            body.latitude?.let {landmark.latitude =  it}
            body.longitude?.let {landmark.longitude =  it}

            return landmark.toLandmark()
    }

    /*
        Point Of Interest
     */

        // placeholder poi created in recordLandmark()

//    @Transactional
//    fun recordPoi(id: UUID, name: String, description: String): PointOfInterest {
//        val landmark = landmarkRepository.findByIdOrNull(id)?: throw LandmarkNotFoundException("Landmark with $id not found")
//
//        val trimmedName = name.trim()
//        val trimmedDescription = description.trim()
//
//        val savedPointOfInterest = pointOfInterestRepository.saveAndFlush(
//            PointOfInterestEntity(
//                landmark = landmark,
//                name = trimmedName,
//                description = trimmedDescription,
//            )
//        ).toPointOfInterest()
//
//        return savedPointOfInterest
//    }

    fun getPoiByLandmarkId(
        id: UUID
    ): PointOfInterest {
        val pointOfInterest =
            pointOfInterestRepository.findByLandmark_Id(id)
                ?: throw PointOfInterestNotFoundException("Poi with $id not found")

        return pointOfInterest.toPointOfInterest()
    }

    fun getAllPois(): List<PointOfInterest> {
        val pointOfInterests = pointOfInterestRepository.findAll()

        return pointOfInterests.map { it.toPointOfInterest() }.sortedByDescending { it.updatedAt }
    }

    @Transactional
    fun deletePoi(id: UUID) {
        pointOfInterestRepository.deleteById(id)
    }

    @Transactional
    fun updatePoi(id: UUID, body: UpdatePoiRequest): PointOfInterest {
        val pointOfInterest =
            pointOfInterestRepository.findByIdOrNull(id)
                ?: throw PointOfInterestNotFoundException("Poi with $id not found")

        body.name?.let {pointOfInterest.name =  it}
        body.description?.let {pointOfInterest.description =  it}

        return pointOfInterest.toPointOfInterest()
    }


    /*
       Landmark Image
    */
    @Transactional
    fun attachImageToLandmark(id: UUID, imagePath: String, caption: String?): LandmarkImage {
        val landmark = landmarkRepository.findByIdOrNull(id)?: throw LandmarkNotFoundException("Landmark with $id not found")
        val savedLandmarkImage = landmarkImageRepository.saveAndFlush(
            LandmarkImageEntity(
                image_path = imagePath,
                landmark = landmark,
                caption = caption
            )
        )
        return savedLandmarkImage.toLandmarkImage()
    }

    fun getImagesForLandmark(
        landmarkId: UUID
    ): List<LandmarkImage> {

        return landmarkImageRepository
            .findAllByLandmark_Id(landmarkId)
            .map { image ->

                LandmarkImage(
                    id = image.id!!,
                    imageUrl = storageService.getPublicUrl(
                        image.image_path
                    ),
                    caption = image.caption,
                    landmarkId = image.landmark.id!!,
                    updatedAt = image.updatedAt ?: Instant.now()
                )
            }
    }

    @Transactional
    fun deleteLandmarkImage(landmarkImageId: UUID) {
        println("admin service deleteLandmarkImage $landmarkImageId")
        landmarkImageRepository.deleteById(landmarkImageId)
    }

    fun getLandmarkImagePath(id: UUID): String {
        val landmarkImageId = landmarkImageRepository.findByIdOrNull(id)
        ?: throw LandmarkNotFoundException("Landmark with $id not found")

        println("admin service landmark image id path: ${landmarkImageId.image_path}")

        //return landmarkImageId.image_path
        println("print image_path ${landmarkImageId.image_path}")
        return landmarkImageId.image_path
    }


    /*
      Point Of Interest Image
   */
    @Transactional
    fun attachImageToPoi(id: UUID, imagePath: String, caption: String?): PointOfInterestImage {
        val poi = pointOfInterestRepository.findByIdOrNull(id)?: throw PointOfInterestNotFoundException("Poi with $id not found")
        val savedPointOfInterestImage = pointOfInterestImageRepository.saveAndFlush(
            PointOfInterestImageEntity(
                image_path = imagePath,
                poi = poi,
                caption = caption
            )
        )
        return savedPointOfInterestImage.toPoiImage()
    }

    fun getPoiImagePath(id: UUID): String {
        val poiImageId = pointOfInterestImageRepository.findByIdOrNull(id)
            ?: throw PointOfInterestNotFoundException("POI image with $id not found")


        println("admin service POI image id path: ${poiImageId.image_path}")


        //return landmarkImageId.image_path
        return poiImageId.image_path
    }

    fun getImagesForPoi(
        poiId: UUID
    ): List<PointOfInterestImage> {

        return pointOfInterestImageRepository
            .findAllByPoi_Id(poiId)
            .map { image ->

                PointOfInterestImage(
                    id = image.id!!,
                    imageUrl = storageService.getPublicUrl(
                        image.image_path
                    ),
                    caption = image.caption,
                    poiId =image.poi.id!!,
                    updatedAt = image.updatedAt ?: Instant.now()
                )
            }

    }

    @Transactional
    fun deletePoiImage(id: UUID) {
        pointOfInterestImageRepository.deleteById(id)
    }
}