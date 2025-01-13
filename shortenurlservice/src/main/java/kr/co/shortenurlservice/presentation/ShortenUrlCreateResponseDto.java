package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.domain.ShortenUrl;

public class ShortenUrlCreateResponseDto {
    private String originalUrl;
    private String shortenUrlKey;

    // DTO <-> domain obj 변환
    public ShortenUrlCreateResponseDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrlKey();
    }

    // JSON 변환
    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
