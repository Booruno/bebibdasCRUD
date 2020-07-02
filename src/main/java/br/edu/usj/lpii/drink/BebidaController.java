package br.edu.usj.lpii.drink;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping
public class BebidaController {

    @Autowired
    BebidaRepository bebidaRepository;
    
    @GetMapping(value="/")
    public ModelAndView getListar() {
        // listar todas as bebibas
        List<Bebida> lista = bebidaRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lista",lista);
        return modelAndView;
    }
    
    @GetMapping(value="/mostrar/{id}")
    public ModelAndView getMostrar (@PathVariable Long id) {
        // mostrar bebida referente ao id

        Bebida bebida = bebidaRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("mostrar");
        modelAndView.addObject("bebida",bebida);
        return modelAndView;
    }

    @GetMapping(value="/cadastrar")
    public ModelAndView getCadastrar() {
        // retorna o formulário para o usuário preencher
        Bebida bebida = new Bebida();
        
        ModelAndView modelAndView = new ModelAndView("cadastrar");
        modelAndView.addObject("bebida",bebida);

        return modelAndView;
    }

    @PostMapping(value="/cadastrar")
    public ModelAndView postCadastrar(Bebida bebida){
        // recebe a bebida preenchida no formulário e mandar gravar no banco
        bebidaRepository.save(bebida);
        
        ModelAndView modelAndView = new ModelAndView("mostrar");
        modelAndView.addObject("bebida",bebida);
                
        return modelAndView;
    }
    
    @GetMapping(value="/deletar/{id}")
    public ModelAndView getDeletar(@PathVariable Long id) {
        // deleta bebiba referente ao id
        bebidaRepository.deleteById(id);

        List<Bebida> lista = bebidaRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lista",lista);
                
        return modelAndView;
        
    }
    
    @GetMapping(value="/editar/{id}")
    public ModelAndView getEditar(@PathVariable Long id) {
        
        Bebida bebida = bebidaRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastrar");
        modelAndView.addObject("bebida",bebida);
                
        return modelAndView;
        
    }

    @GetMapping(value="/pesquisar/{nome}")
    public List<Bebida> getPesquisarNome(@PathVariable String nome) {
        List<Bebida> lista = bebidaRepository.findByNome(nome);
        return lista;
    }
    

}