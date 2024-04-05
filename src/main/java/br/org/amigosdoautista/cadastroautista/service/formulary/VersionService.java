package br.org.amigosdoautista.cadastroautista.service.formulary;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.org.amigosdoautista.cadastroautista.model.repository.formulary.VersionRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.FormSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.VersionSchema;
import br.org.amigosdoautista.cadastroautista.web.error.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class VersionService {

    private static final String ID_VERSION = "idVersion";

    private final VersionRepository versionRepository;

    public VersionSchema createNewVersion(@Valid FormSchema form) {
        List<VersionSchema> versionsList = versionRepository.findByForm(form,
                Sort.by(ID_VERSION).descending());

        if (!versionsList.isEmpty()) {
            VersionSchema lastVersion = versionsList.get(0);
            lastVersion.setCanceled(true);
            versionRepository.save(lastVersion);
        }

        VersionSchema version = new VersionSchema();
        version.setForm(form);

        return versionRepository.save(version);
    }

    public VersionSchema getActiveVersion(FormSchema form) {
        return versionRepository.findActiveByForm(form.getId())
                .orElseThrow(() -> new DataNotFoundException(form.getId()));
    }

}
