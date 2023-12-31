## API Rest App with SQL (SpringBoot - Swagger)


Open API - Swagger 

<table>
   <tr>
      <td><p>All Methods</p></td>
      <td><p>Save</p></td>
   </tr>
   <tr>
      <td> 
      <img src="S05T01N02DebonMiquel/src/main/resources/static/images/allMethodss.png" alt="Italian 404">
      </td>
      <td>
      <img src="S05T01N02DebonMiquel/src/main/resources/static/images/save.png" alt="Italian 500">
      </td>
   </tr>
  <tr>
      <td><p>Update</p></td>
      <td><p>Delete</p></td>
  </tr>
  <tr>
      <td> 
      <img src="S05T01N02DebonMiquel/src/main/resources/static/images/update.png" alt="Italian 404">
      </td>
      <td>
      <img src="S05T01N02DebonMiquel/src/main/resources/static/images/delete.png" alt="Italian 500">
      </td>
  </tr>

  <tr>
      <td><p>GetAll</p></td>
      <td><p>GetOne</p></td>
  </tr>
  <tr>
      <td> 
      <img src="S05T01N02DebonMiquel/src/main/resources/static/images/getAll.png" alt="Italian 404">
      </td>
      <td>
      <img src="S05T01N02DebonMiquel/src/main/resources/static/images/getOne.png" alt="Italian 500">
      </td>
  </tr>

</table>



References:
- [Swagger - Extended course](https://www.youtube.com/watch?v=0vqgWQIVfMI&t=2538s)
- [Swagger - Summary course](https://www.youtube.com/watch?v=2o_3hjUPAfQ)
- [Bezkoder Swaggger Documnetation](https://www.bezkoder.com/swagger-3-annotations/)
- [Multiple Implementation SpringBoot Injection Conflict](https://www.springcloud.io/post/2022-04/spring-selective-injection/#gsc.tab=0)
    ```
    @Resource(name = "demoServiceBeijing")  
    IDemoService demoService;
    ```
- [ResponseStatusException](https://www.baeldung.com/spring-response-status-exception)

    ```
    @GetMapping("/actor/{id}")
    public String getActorName(@PathVariable("id") int id) {
        try {
            return actorService.getActor(id);
        } catch (ActorNotFoundException ex) {
            throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Actor Not Found", ex);
        }
    }
    ```