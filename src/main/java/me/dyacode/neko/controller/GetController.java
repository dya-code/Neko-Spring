package me.dyacode.neko.controller;

import me.dyacode.neko.dto.NekoDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class GetController {

    public String BASE_URL = "https://nekos.moe/api/v1/random/image?nsfw=false";

    @GetMapping("/neko")
    public NekoDto GetNeko() throws ParseException {
        // String API_URL = BASE_URL + "/neko";

        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(BASE_URL, String.class);
        System.out.println(resp);

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        JSONArray arr = (JSONArray) obj.get("images");
        JSONObject arrbody = (JSONObject) arr.get(0);
        String artist = arrbody.get("artist").toString();
        String id = arrbody.get("id").toString();

        NekoDto nekoDto = new NekoDto();
        
        nekoDto.setArtist(artist);
        nekoDto.setId(id);
        nekoDto.setImage("https://nekos.moe/image/" + id);

        return nekoDto;
    }

}