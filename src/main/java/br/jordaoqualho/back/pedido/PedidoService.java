package br.jordaoqualho.back.pedido;

import javax.transaction.Transactional;

import com.github.javafaker.Faker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
      Faker faker = new Faker();

    public Page<Pedido> obterTodos(Pageable pageRequest, String termo) {
        if (termo == null || termo.trim().length() == 0) {
             return repository.findAll(pageRequest);            
        }
        return repository.findByIdLike(pageRequest, "%" + termo + "%");
    }  

    public Pedido obterPeloId(String id) {
        return repository.findById(id).orElseGet(Pedido::new);
    }

    public void excluirPeloId(String id) {
        repository.deleteById(id);
    }

    public void excluirTodos() {
        repository.deleteAll();
    }


    public Pedido salvar(Pedido Inspecao) {
        return repository.save(Inspecao);
    }    

   
}