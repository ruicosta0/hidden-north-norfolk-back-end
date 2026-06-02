package com.riprojects.sharrington.domain.exception

import com.riprojects.sharrington.domain.model.LandmarkId
import java.util.UUID

class LandmarkNotFoundException(
    message: String
): RuntimeException(message)