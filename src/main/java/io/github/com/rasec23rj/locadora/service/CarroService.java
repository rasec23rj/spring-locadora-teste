package io.github.com.rasec23rj.locadora.service;

import io.github.com.rasec23rj.locadora.entity.CarroEntity;
import io.github.com.rasec23rj.locadora.exception.EntityNotFoundException;
import io.github.com.rasec23rj.locadora.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public CarroEntity save(CarroEntity carro) {
        if (carro.getValorDiaria() <= 0) {
            throw new IllegalArgumentException("Preço diaria não pode ser negativo");
        }
        return carroRepository.save(carro);
    }

    public  CarroEntity update(Long id, CarroEntity carroUpdate){
        var byId = searchById(id);

        byId.setAno(carroUpdate.getAno());
        byId.setModelo(carroUpdate.getModelo());
        byId.setValorDiaria(carroUpdate.getValorDiaria());

        return carroRepository.save(byId);

    }

    public void deletar(Long id){
        var byId = searchById(id);
        carroRepository.deleteById(id);
    }
    public CarroEntity searchById(Long id){
        return  carroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Carro não encontrado"));
    }

    public List<CarroEntity> findAll(){
        return carroRepository.findAll();
    }
}
