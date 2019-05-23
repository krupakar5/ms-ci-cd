package io.msa.content.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.msa.content.models.Content;
import io.msa.content.repositories.ContentRepository;

@Service
public class ContentService {

	@Autowired
	private ContentRepository contentRepository;

	public Content save(Content content) {
		return contentRepository.save(content);
	}

	public List<Content> fetchById(Long ctnId) {
		return contentRepository.findByCtnId(ctnId);
	}

	public Iterable<Content> findAll() {
		return contentRepository.findAll();
	}

	public List<Content> fetchByName(String name) {
		return contentRepository.findByName(name);
	}

	public void deleteById(Long ctnId) {
		contentRepository.delete(ctnId);
	}
}
