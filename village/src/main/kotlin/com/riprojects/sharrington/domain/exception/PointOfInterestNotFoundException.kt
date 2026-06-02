package com.riprojects.sharrington.domain.exception

import com.riprojects.sharrington.domain.model.LandmarkId
import java.util.UUID

class PointOfInterestNotFoundException(
    message: String
): RuntimeException(message)