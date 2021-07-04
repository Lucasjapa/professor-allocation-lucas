package com.project.professorallocation.controller;

import com.project.professorallocation.entity.Course;
import com.project.professorallocation.service.CourseService;
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

@Api(tags = {"courses"})
@RestController
@RequiredArgsConstructor
@RequestMapping(
        path = "/courses",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CourseController {

    private final CourseService courseService;

    //--------------------------------READ--------------------------------
    // @GetMapping(path = "/") caminho default
    @ApiOperation(value = "Find all Courses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Course>> findAll(
            @RequestParam(name = "name", required = false) String name){
        return new ResponseEntity<>(courseService.findAll(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a course by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{course_id}")
    public ResponseEntity<Course> findById(
            @PathVariable(name = "course_id") Long id)  {
        try{
            return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //------------------------------------------------------------------------------

    //--------------------------------CREATE--------------------------------
    // @PostMapping(path = "/") caminho default
    @ApiOperation(value = "Create a course")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        try{
            return new ResponseEntity<>(courseService.create(course), HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //----------------------------------------------------------------------------------

    //--------------------------------UPDATE--------------------------------
    // @PutMapping(path = "/") caminho default
    @ApiOperation(value = "Update a course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "0K"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(
            path = "/{course_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Course> update(
            @PathVariable(name = "course_id") Long id,
            @RequestBody Course course) {
        course.setId(id);
        try{
            if(courseService.update(course) == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(courseService.update(course), HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //----------------------------------------------------------------------------------

    //--------------------------------DELETE--------------------------------
    //@DeleteMapping (path = "/") caminho default
    @ApiOperation(value = "Delete a course by Id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{course_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "course_id") Long id)  {
        try{
            courseService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@DeleteMapping (path = "/") caminho default
    @ApiOperation(value = "Delete All Courses")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        courseService.deleteALL();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //----------------------------------------------------------------------------------
}
