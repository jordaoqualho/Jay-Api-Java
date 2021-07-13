package br.jordaoqualho.back.produtos;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service; 
   

    @GetMapping
    public Page<Produto> get(Pageable pageRequest,  @RequestParam(name = "termo", required = false) String termo, @RequestParam(name = "tipo", required = false) String tipo) {
        return service.obterTodos(pageRequest, termo, tipo);
    }
 
    @GetMapping("/{idParaEditar}")
    public Produto getById(@PathVariable("idParaEditar") String idParaEditar) {
        return service.obterPeloId(idParaEditar);
    }

    @PutMapping("/{id}")
    public void put(@PathVariable String id, @RequestBody Produto ProdutoEditado) {
        service.salvar(ProdutoEditado);
    }

    @PostMapping("/gerar")
    public String postGerarProdutos() {
        service.gerarProdutos();
        return "Produtos gerados com sucesso!";
    }

    @PostMapping
    public String post(@RequestBody Produto novo) {
        Produto ProdutoSalvo = service.salvar(novo);
        return ProdutoSalvo.getId();
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.excluirPeloId(id);
    }
    
    @DeleteMapping("/excluir-todos")
    public void deleteAll() {
        service.excluirTodos();
    }


}