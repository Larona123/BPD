package services.intf;

import entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectServiceIntf {
    public abstract List<Project> getAll();

    public abstract Optional<Project> getById(Long id);

    public abstract Project save(Project project);

    public abstract void delete(Long id);
}
