package com.project.professorallocation.controller;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.service.AllocationService;
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

@Api(tags = {"allocations"})
@RestController
@RequiredArgsConstructor
@RequestMapping(
        path = "/allocations",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AllocationController {

    private final AllocationService allocationService;

    //--------------------------------READ--------------------------------
    // @GetMapping(path = "/") caminho default
    @ApiOperation(value = "Find all Allocations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Allocation>> findAll(){
        return new ResponseEntity<>(allocationService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a allocation by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{allocation_id}")
    public ResponseEntity<Allocation> findById(
            @PathVariable(name = "allocation_id") Long id)  {
        try{
            return new ResponseEntity<>(allocationService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Find a allocation by professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/professor/{professor_id}")
    public ResponseEntity<List<Allocation>> findAllocationByProfessor(
            @PathVariable(name = "professor_id") Long id)  {

            return new ResponseEntity<>(allocationService.findAllocationByProfessorId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a allocation by course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/course/{course_id}")
    public ResponseEntity<List<Allocation>> findAllocationByCourse(
            @PathVariable(name = "course_id") Long id)  {

        return new ResponseEntity<>(allocationService.findAllocationByCourseId(id), HttpStatus.OK);
    }
    //------------------------------------------------------------------------------

    //--------------------------------CREATE--------------------------------
    // @PostMapping(path = "/") caminho default
    @ApiOperation(value = "Create a allocation")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Allocation> create(@RequestBody Allocation allocation)  {
        try{
            return new ResponseEntity<>(allocationService.create(allocation), HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //----------------------------------------------------------------------------------

    //--------------------------------UPDATE--------------------------------
    // @PutMapping(path = "/") caminho default
    @ApiOperation(value = "Update a Allocation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "0K"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(
            path = "/{update_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Allocation> update(
            @PathVariable(name = "update_id") Long id,
            @RequestBody Allocation allocation) {
        allocation.setId(id);
        try{
            if(allocationService.update(allocation) == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(allocationService.update(allocation), HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //----------------------------------------------------------------------------------

    //--------------------------------DELETE--------------------------------
    //@DeleteMapping (path = "/") caminho default
    @ApiOperation(value = "Delete a allocation by Id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{allocation_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id)  {
        try{
            allocationService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@DeleteMapping (path = "/") caminho default
    @ApiOperation(value = "Delete All Allocations")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        allocationService.deleteALL();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //----------------------------------------------------------------------------------
}
