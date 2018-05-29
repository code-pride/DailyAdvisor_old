package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.entity.VerificationToken;
import com.advisor.repository.VerificationTokenRepository;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service("verificationTokenService")
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private static final String VERIFICATION_TOKEN_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.verificationToken";

    private static final String VERIFICATION_TOKEN_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.verificationToken";

    @Autowired
    @Qualifier("verificationTokenRepository")
    private VerificationTokenRepository repository;

    @Override
    public VerificationToken create(VerificationToken verificationToken) throws DataRepositoryException {
        if(verificationToken.getId() == null || !repository.findById(verificationToken.getId()).isPresent()) {
            return repository.save(verificationToken);
        } else {
            throw new EntityExists(VERIFICATION_TOKEN_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {

            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(VERIFICATION_TOKEN_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public List<VerificationToken> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<VerificationToken> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public VerificationToken update(VerificationToken verificationToken) throws DataRepositoryException, NoSuchElementException {
        if(repository.findById(verificationToken.getId()).isPresent()){
            return repository.save(verificationToken);
        } else {
            throw new EntityNotFoundException(VERIFICATION_TOKEN_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public User confirmToken(String token) throws EntityNotFoundException {
        VerificationToken verificationToken = repository.findOneByToken(token);

        if(verificationToken != null && (verificationToken.getExpiryDate().compareTo(OffsetDateTime.now()))>0){
            repository.delete(verificationToken);
            return verificationToken.getUser();
        }
        else {
            throw new EntityNotFoundException(VERIFICATION_TOKEN_NOT_FOUND_MESSAGE_CODE);
        }
    }
}
