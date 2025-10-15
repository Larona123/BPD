package services.intf;

import entity.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueServiceIntf {
    List<Issue> getAll();

    Optional<Issue> getById(Long id);

    Issue save(Issue issue);

    void delete(Long id);
}
