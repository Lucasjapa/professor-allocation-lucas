package com.project.professorallocation.controller;

import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.service.ProfessorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"professors"})
@RestController
@RequiredArgsConstructor
@RequestMapping(
        path = "/professors",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProfessorController {

    private final ProfessorService professorService;

    // @GetMapping(path = "/") caminho default
    @GetMapping
    public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "name", required = false) String name){
            return new ResponseEntity<>(professorService.findAll(name), HttpStatus.OK);
    }

    @GetMapping(path = "/{professor_id}")
    public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id)  {
       try{
           return new ResponseEntity<>(professorService.findById(id), HttpStatus.OK);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
        return null;
    }

    // @PostMapping(path = "/") caminho default
    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody Professor professor) throws Exception {
        try{
            return new ResponseEntity<>(professorService.create(professor), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // @PutMapping(path = "/") caminho default
    @ApiOperation(value = "Update a professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "0K"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(
            path = "/{professor_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Professor> update(
            @PathVariable(name = "professor_id") Long id,
            @RequestBody Professor professor) {
        professor.setId(id);
        try{
            if(professorService.update(professor) == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(professorService.update(professor), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{professor_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) throws Exception {
        professorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@DeleteMapping (path = "/") caminho default
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        professorService.deleteALL();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
