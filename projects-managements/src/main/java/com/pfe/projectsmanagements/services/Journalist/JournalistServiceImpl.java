package com.pfe.projectsmanagements.services.Journalist;

import com.pfe.projectsmanagements.Dto.Journalist.JournalistRequestDto;
import com.pfe.projectsmanagements.Dto.Journalist.JournalistResponseDto;
import com.pfe.projectsmanagements.dao.JournalistRepository;
import com.pfe.projectsmanagements.dao.RoleRepository;
import com.pfe.projectsmanagements.entities.Journalist;
import com.pfe.projectsmanagements.entities.JournalistRole;
import com.pfe.projectsmanagements.entities.others.EmailDetails;
import com.pfe.projectsmanagements.exceptions.Journalist.JournalistExisteAlready;
import com.pfe.projectsmanagements.exceptions.Journalist.JournalistNotFoundException;
import com.pfe.projectsmanagements.exceptions.Role.RoleNotFoundException;
import com.pfe.projectsmanagements.mappers.JournalistMapper;
import com.pfe.projectsmanagements.services.Email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JournalistServiceImpl implements JournalistService{

    private JournalistRepository journalistRepository ;

    private RoleRepository roleRepository;

    private JournalistMapper journalistMapper ;

    private Random random ;

    private EmailService emailService ;

    private final static Logger logger = LoggerFactory.getLogger(Journalist.class);

    @Autowired
    public  JournalistServiceImpl(JournalistRepository journalistRepository  , EmailService emailService , RoleRepository roleRepository,  Random random )
    {
        this.journalistRepository = journalistRepository ;
        this.roleRepository = roleRepository ;
        this.journalistMapper = JournalistMapper.getInstance();
        this.emailService = emailService ;
        this.random = random ;
    }

    @Override
    public JournalistResponseDto saveJournalist(JournalistRequestDto requestDto) {

        Optional <Journalist> journalistOptionalByUsername = journalistRepository.findByUserName(requestDto.getUserName());
        Optional <Journalist> journalistOptionalByEmail = journalistRepository.findByEmail(requestDto.getEmail());

        if(journalistOptionalByEmail.isPresent() || journalistOptionalByUsername.isPresent())
            throw new JournalistExisteAlready();

        Journalist journalist = journalistMapper.DtoToEntity(requestDto);
        journalist.setId(random.nextLong());
        Journalist journalistResponse = journalistRepository.save(journalist) ;
        if(Objects.nonNull(journalistResponse)) {
            JournalistResponseDto journalistResponseDto = journalistMapper.EntityToDto(journalistResponse);
            logger.info("What im gonna returning : {} ", journalistResponseDto.toString());
            EmailDetails emailDetails = EmailDetails
                    .builder()
                    .subject("ACCOUNT CREATING ")
                    .msgBody("Hey " +journalist.getUserName() +
                            " This is your password " + UUID.randomUUID().toString()
                            +"For any requirement contact me , welcome !"
                            )
                    .recipient(journalist.getEmail())
                    .build();
            emailService.sendSimpleMail(emailDetails);
            return journalistResponseDto;
        }
        return null ;
    }

    @Override
    public Journalist addRoleToUser(Long id , String roleName) {
        Optional<Journalist> journalistOptional = journalistRepository.findById(id);
        if(journalistOptional.isPresent())
        {
            Journalist journalist  = journalistOptional.get();
            Optional<JournalistRole> role = roleRepository.findByRole(roleName);
            if(role.isPresent()) {
                journalist.getRoles().add(role.get());
                journalistRepository.save(journalist);
            }
            throw new RoleNotFoundException();
        }
        throw new JournalistNotFoundException();
    }

    @Override
    public JournalistResponseDto getJournalist(Long id) {
        Optional<Journalist> journalistOptional = journalistRepository.findById(id);
        if(journalistOptional.isPresent())
            return journalistMapper.EntityToDto(journalistOptional.get());
        throw new JournalistNotFoundException();
    }

    @Override
    public Journalist getFullJournalist(Long id) {
        Optional<Journalist> journalistOptional = journalistRepository.findById(id);
        if(journalistOptional.isPresent())
                return journalistOptional.get();
        throw new JournalistNotFoundException();
    }

    @Override
    public Journalist getJournalistByUsername(String username) {
        Optional<Journalist> journalist = journalistRepository.findByUserName(username);
        if(journalist.isPresent())
            return journalist.get();
        throw new JournalistNotFoundException();
    }

    @Override
    public List<JournalistResponseDto> getAllJournalistes() {
        List<Journalist> journalists = journalistRepository.findAll();
        return journalists
                .stream()
                .map(journalist -> {
                    return  journalistMapper.EntityToDto(journalist);
                }).collect(Collectors.toList());
    }

    @Override
    public List<Journalist> getFullJournalistes() {
        return journalistRepository.findAll();
    }

    @Override
    public JournalistResponseDto updateJournalist(JournalistRequestDto journalistRequestDto, Long id) {
        Optional<Journalist> journalistOptional = journalistRepository.findById(id);
        if(journalistOptional.isPresent())
        {
            Journalist journalist = journalistOptional.get();
            BeanUtils.copyProperties(journalistRequestDto,journalist);
            journalistRepository.save(journalist);
        }
        throw new JournalistNotFoundException() ;
    }

    @Override
    public boolean deleteJournalist(Long id) {
        Optional<Journalist> journalistOptional = journalistRepository.findById(id);
        if(journalistOptional.isPresent())
        {
            Journalist journalist = journalistOptional.get();
            journalistRepository.delete(journalist);
            return  true ;
        }
        return false ;
    }
}
