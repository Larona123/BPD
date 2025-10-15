package services.impl;

import entity.Issue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.IssueRepository;
import services.intf.IssueServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class IssueServiceImpl implements IssueServiceIntf {

    private final IssueRepository issueRepository;

    @Override
    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    @Override
    public Optional<Issue> getById(Long id) {
        return issueRepository.findById(id);
    }

    @Override
    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public void delete(Long id) {
        issueRepository.deleteById(id);
    }
}
