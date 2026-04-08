package com.cms.service;

import com.cms.mapper.TranslationMapper;
import com.cms.model.Translation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface TranslationService {
    Translation findById(Long id);
    Translation findByKeyAndLang(String key, String lang);
    List<Translation> findByLang(String lang);
    List<Translation> findAll();
    Translation save(Translation translation);
    Translation update(Translation translation);
    boolean delete(Long id);
    void upsert(String key, String lang, String value);
}

@Slf4j
@Service
@RequiredArgsConstructor
class TranslationServiceImpl implements TranslationService {

    private final TranslationMapper translationMapper;

    @Override
    public Translation findById(Long id) {
        return translationMapper.findById(id);
    }

    @Override
    public Translation findByKeyAndLang(String key, String lang) {
        return translationMapper.findByKeyAndLang(key, lang);
    }

    @Override
    public List<Translation> findByLang(String lang) {
        return translationMapper.findByLang(lang);
    }

    @Override
    public List<Translation> findAll() {
        return translationMapper.findAll();
    }

    @Override
    @Transactional
    public Translation save(Translation translation) {
        log.info("Saving translation key: {} lang: {}", translation.getKey(), translation.getLang());
        translation.setCreatedDate(LocalDateTime.now());
        translation.setUpdatedDate(LocalDateTime.now());
        translationMapper.insert(translation);
        return translation;
    }

    @Override
    @Transactional
    public Translation update(Translation translation) {
        log.info("Updating translation id: {}", translation.getId());
        translation.setUpdatedDate(LocalDateTime.now());
        translationMapper.update(translation);
        return translation;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        log.info("Deleting translation id: {}", id);
        return translationMapper.deleteById(id) > 0;
    }

    @Override
    @Transactional
    public void upsert(String key, String lang, String value) {
        Translation translation = Translation.builder()
                .key(key)
                .lang(lang)
                .value(value)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        translationMapper.upsert(translation);
    }
}
