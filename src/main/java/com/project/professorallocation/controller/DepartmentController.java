package com.project.professorallocation.controller;

import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Department;
import com.project.professorallocation.service.DepartmentService;
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

@Api(tags = {"departments"})
@RestController
@RequiredArgsConstructor
@RequestMapping(
        path = "/departments",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class DepartmentController {

    private final DepartmentService departmentService;

    //--------------------------------READ--------------------------------
    // @GetMapping(path = "/") caminho default
    @ApiOperation(value = "Find all Departments")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Department>> findAll(
            @RequestParam(name = "name", required = false) String name){
        return new ResponseEntity<>(departmentService.findAll(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a department by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{department_id}")
    public ResponseEntity<Department> findById(
            @PathVariable(name = "department_id") Long id)  {
        try{
            return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //------------------------------------------------------------------------------

    //--------------------------------CREATE--------------------------------
    // @PostMapping(path = "/") caminho default
    @ApiOperation(value = "Create a department")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        try{
            return new ResponseEntity<>(departmentService.create(department), HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //----------------------------------------------------------------------------------

    //--------------------------------UPDATE--------------------------------
    // @PutMapping(path = "/") caminho default
    @ApiOperation(value = "Update a department")
    @ApiResponses({
            @ApiResponse(code = 200, message = "0K"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(
            path = "/{department_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Department> update(
            @PathVariable(name = "department_id") Long id,
            @RequestBody Department department) {
        department.setId(id);
        try{
            if(departmentService.update(department) == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(departmentService.update(department), HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //----------------------------------------------------------------------------------

    //--------------------------------DELETE--------------------------------
    //@DeleteMapping (path = "/") caminho default
    @ApiOperation(value = "Delete a department by Id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{department_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id)  {
        try{
            departmentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@DeleteMapping (path = "/") caminho default
    @ApiOperation(value = "Delete All Departments")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        departmentService.deleteALL();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //----------------------------------------------------------------------------------
}
