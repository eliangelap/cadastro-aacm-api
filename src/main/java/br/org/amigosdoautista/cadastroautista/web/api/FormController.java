package br.org.amigosdoautista.cadastroautista.web.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.amigosdoautista.cadastroautista.model.dao.formulary.ActiveFormResponse;
import br.org.amigosdoautista.cadastroautista.model.dao.formulary.FormRequest;
import br.org.amigosdoautista.cadastroautista.model.dao.formulary.ShortFormResponse;
import br.org.amigosdoautista.cadastroautista.service.formulary.FormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ShortFormResponse createNew(@RequestBody @Valid FormRequest form) {
        return formService.createNew(form);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActiveFormResponse listByID(@NonNull @PathVariable Integer id) {
        return formService.getActiveFormWithQuestions(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShortFormResponse> list() {
        return formService.getForms();
    }

    @DeleteMapping(value = "/cancel/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShortFormResponse cancelForm(@NonNull @PathVariable Integer id) {
        return formService.cancelForm(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShortFormResponse updateForm(@NonNull @PathVariable Integer id, @RequestBody @Valid FormRequest form) {
        return formService.updateForm(id, form);
    }

}
