package edu.unc.academico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.unc.academico.domain.Departamento;
import edu.unc.academico.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImp implements DepartamentoService {
	
	@Autowired
	private DepartamentoRepository dptoRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Departamento> listarDpto() {
		return (List<Departamento>) dptoRepo.findAll();
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Departamento> buscarPorIdDpto(Long idDpto) {
		return dptoRepo.findById(idDpto);
	}

	@Override
	public Departamento guardarDpto(Departamento dpto) {
		return dptoRepo.save(dpto);

	}

	@Override
	public void eliminarDpto(Long idDpto) {
		dptoRepo.deleteById(idDpto);
	}

}
