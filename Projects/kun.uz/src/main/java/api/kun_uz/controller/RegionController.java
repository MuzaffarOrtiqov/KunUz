package api.kun_uz.controller;

import api.kun_uz.dto.RegionUpdateDTO;
import api.kun_uz.dto.region.RegionCreateDTO;
import api.kun_uz.dto.region.RegionResponseDTO;
import api.kun_uz.entity.RegionEntity;
import api.kun_uz.enums.Language;
import api.kun_uz.service.RegionService;
import api.kun_uz.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")

public class RegionController {
    private final RegionService regionService;
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }
     @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody RegionCreateDTO regionDTO) {
         return ResponseEntity.ok(regionService.createRegion(regionDTO));
     }
    @PutMapping ("/update/{id}")
    public ResponseEntity<RegionResponseDTO>update(@PathVariable String id, @RequestBody RegionUpdateDTO regionDTO){

            RegionResponseDTO regionResponseDTO = regionService.updateRegion(id, regionDTO);
            return ResponseEntity.ok(regionResponseDTO);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(regionService.deleteRegion(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionResponseDTO>> getAll() {
        return ResponseEntity.ok(regionService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<RegionResponseDTO>> getByLang(@RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(regionService.getByLang(lang));
    }




}
