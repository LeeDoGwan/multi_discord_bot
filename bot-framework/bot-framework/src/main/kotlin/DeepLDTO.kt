package org.example

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

class DeepLDTO {

    data class DeepLTranslateRequest(
        val text: List<String>,

        @JsonProperty("target_lang")
        val targetLang: String
    )

    data class DeepLTranslateResponse(
        val translations: List<Translation>
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Translation(
        @JsonProperty("detected_source_language")
        val detectedSourceLanguage: String,

        val text: String
    )
}