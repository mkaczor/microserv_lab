package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/client")
public class ClientRest {

    private ClientDAO clientDAO;
    private ClientRepository clientRepository;

    @Autowired
    public ClientRest(ClientDAO clientDAO, ClientRepository clientRepository) {
        this.clientDAO = clientDAO;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<ClientEntity> create(@RequestParam String name) {
        return ResponseEntity.ok(clientDAO.create(name));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientEntity> update(@PathVariable int id, @RequestParam String name) {
        boolean update = clientDAO.update(id, name);
        if (update) {
            return ResponseEntity.ok(clientRepository.findOne(id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClientEntity> delete(@PathVariable int id) {
        ClientEntity one = clientRepository.findOne(id);
        if (one == null) {
            return ResponseEntity.notFound().build();
        }
        clientRepository.delete(id);
        return ResponseEntity.ok(one);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ClientEntity>> getAll() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable  int id) {
        ClientEntity one = clientRepository.findOne(id);
        if (one == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(one);
    }


}
