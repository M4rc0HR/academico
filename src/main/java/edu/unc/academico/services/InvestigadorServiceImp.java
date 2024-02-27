package edu.unc.academico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.unc.academico.domain.Investigador;
import edu.unc.academico.repository.InvestigadorRepository;

@Service
public class InvestigadorServiceImp implements InvestigadorService {
	
	@Autowired
	private InvestigadorRepository invRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Investigador> listarInv() {
		return (List<Investigador>) invRepo.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Investigador> buscarPorIdInv(Long idInv) {
		return invRepo.findById(idInv);
	}

	@Override
	public Investigador guardarInv(Investigador inv) {
		return invRepo.save(inv);
	}

	@Override
	public void eliminarInv(Long idInv) {
		invRepo.deleteById(idInv);

	}

}
