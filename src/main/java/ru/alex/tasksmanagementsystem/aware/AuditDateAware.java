package ru.alex.tasksmanagementsystem.aware;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditDateAware")
public class AuditDateAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityContextHolder
                .getContext()
                .getAuthentication().getName());
    }
}
