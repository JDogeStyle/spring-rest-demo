package com.rest.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.demo.model.Categoria;
import com.rest.demo.repository.CategoriaRepository;
import com.rest.demo.utils.ConvertMapToEntity;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
	private final CategoriaRepository repository;
	
	@Autowired
	public CategoriaServiceImpl(CategoriaRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public Categoria save(Categoria entity) {
		return repository.save(entity);
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Categoria> findByID(Long id) {
		return repository.findById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean exists(Long id) {
		return repository.existsById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Categoria> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Categoria> buscarxDescripcion(String desc) {
		return repository.findByDescripcionIgnoreCaseContaining(desc);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Categoria> buscarxProducto(Long id) {
		return repository.findByProductoId(id);
	}
	
	@Transactional
	@Override
	public Categoria actualizarParcial(Map<String, Object> prop, Long id) {
		return findByID(id)
			.map(c -> {
				c = ConvertMapToEntity.convert(prop, c);
				return repository.save(c);
			})
			.orElseThrow(ResourceNotFoundException::new);
	}

}
