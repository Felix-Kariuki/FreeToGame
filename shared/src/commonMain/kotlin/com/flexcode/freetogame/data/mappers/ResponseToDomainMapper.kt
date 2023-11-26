package com.flexcode.freetogame.data.mappers

import com.flexcode.freetogame.data.dto.FreeToGameDescriptionDto
import com.flexcode.freetogame.data.dto.FreeToGameDto
import com.flexcode.freetogame.data.dto.MinimumSystemRequirementsDto
import com.flexcode.freetogame.data.dto.ScreenshotDto
import com.flexcode.freetogame.domain.models.FreeGame
import com.flexcode.freetogame.domain.models.FreeToGameDescription
import com.flexcode.freetogame.domain.models.MinimumSystemRequirements
import com.flexcode.freetogame.domain.models.Screenshot

internal fun FreeToGameDto.toDomain(): FreeGame {
    return FreeGame(
        this.developer ,
        this.freetogameProfileUrl ,
        this.gameUrl ,
        this.genre ,
        this.id ,
        this.platform ,
        this.publisher ,
        this.releaseDate ,
        this.shortDescription ,
        this.thumbnail ,
        this.title
    )
}

internal fun FreeToGameDescriptionDto.toDomain(): FreeToGameDescription {
    return FreeToGameDescription(
        this.description,
        this.developer,
        this.freetogameProfileUrl,
        this.gameUrl,
        this.genre,
        this.id,
        this.minimumSystemRequirements.toDomain(),
        this.platform,
        this.publisher,
        this.releaseDate,
        this.screenshots.map { it.toDomain() },
        this.shortDescription,
        this.status,
        this.thumbnail,
        this.title
    )
}

internal fun MinimumSystemRequirementsDto.toDomain(): MinimumSystemRequirements {
    return MinimumSystemRequirements(
        this.graphics,
        this.memory,
        this.os,
        this.processor,
        this.storage
    )
}

internal fun ScreenshotDto.toDomain(): Screenshot {
    return Screenshot(image = image)
}