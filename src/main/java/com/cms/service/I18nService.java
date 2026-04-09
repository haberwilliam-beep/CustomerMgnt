package com.cms.service;

import com.cms.mapper.TranslationMapper;
import com.cms.model.Translation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class I18nService {

    private final TranslationMapper translationMapper;
    private final Map<String, Map<String, String>> cache = new ConcurrentHashMap<>();

    public String getMessage(String key, String lang) {
        Map<String, String> langMap = cache.computeIfAbsent(lang, this::loadTranslations);
        return langMap.getOrDefault(key, key);
    }

    public Map<String, String> getAllMessages(String lang) {
        return cache.computeIfAbsent(lang, this::loadTranslations);
    }

    private Map<String, String> loadTranslations(String lang) {
        log.debug("Loading translations for lang: {}", lang);
        List<Translation> translations = translationMapper.findByLang(lang);
        Map<String, String> map = new HashMap<>();
        if (translations != null) {
            translations.forEach(t -> map.put(t.getKey(), t.getValue()));
        }
        return map;
    }

    public void evictCache(String lang) {
        log.info("Evicting translation cache for lang: {}", lang);
        cache.remove(lang);
    }

    public void evictAllCaches() {
        log.info("Evicting all translation caches");
        cache.clear();
    }
}
