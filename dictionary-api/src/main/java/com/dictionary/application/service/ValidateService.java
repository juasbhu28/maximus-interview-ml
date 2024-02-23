package com.dictionary.application.service;

import com.dictionary.application.dto.PostDTO;
import com.dictionary.application.dto.ValidateRequestDto;
import com.dictionary.application.dto.ValidateResponseDto;
import com.dictionary.domain.model.Site;
import com.dictionary.domain.model.Stats;
import com.dictionary.domain.model.Word;
import com.dictionary.infrastructure.persistence.repository.SiteRepositoryImpl;
import com.dictionary.infrastructure.persistence.repository.StatsRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidateService {

    @Autowired
    private SiteRepositoryImpl siteRepository;

    @Autowired
    private StatsRepositoryImpl statsRepository;


    public ValidateResponseDto validate(ValidateRequestDto request){
        try {
            long startTime = System.currentTimeMillis();
            ValidateResponseDto response;
            response = algoritmo(request);

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            savingStats(request, response, executionTime);
            return response;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void savingStats(ValidateRequestDto request, ValidateResponseDto response, long executionTime) throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();
        ObjectMapper objectMapper = new ObjectMapper();
        Stats stats = new Stats();
        stats.setRequestId(uuid.toString());
        stats.setRequest(objectMapper.writeValueAsString(request));
        stats.setResponse(objectMapper.writeValueAsString(response));
        stats.setExecutionTime(executionTime);
        statsRepository.save(stats);
    }

    public ValidateResponseDto algoritmo(ValidateRequestDto request){

        ValidateResponseDto response = new ValidateResponseDto();
        response.setSites(new HashMap<>());


        List<Site> siteList = siteRepository.getAll();

        String[] posts = request.getPosts();
        String[] sites = siteList.stream().map(Site::getCode).toArray(String[]::new);
        String[][] wordsBySite = converSitesToWordsArray(siteList);

        // Iterin over each site
        for (int i = 0; i < sites.length; i++) {
            String site = sites[i];
            String[] words = wordsBySite[i];

            // Creating a set of words for the current site
            Set<String> wordsSetCurrentSite = new HashSet<>(Arrays.asList(words));

            // Count the number of posts associated with the site
            int countPostAssociated = 0;
            List<String> postsFounded = new ArrayList<>();

            // Iterating over each post
            for (String post : posts) {

                // Split the post into words
                String[] wordsByPost = post.split("\\s+");

                // Verifying if the post contains any word from the site
                for (String word : wordsByPost) {

                    if (wordsSetCurrentSite.contains( word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase() )) {
                        // Count and store the post
                        countPostAssociated++;
                        postsFounded.add(post);
                        break;
                    }
                }
            }

            if(countPostAssociated > 0) {
                PostDTO postDTO = new PostDTO();
                postDTO.setTotal(countPostAssociated);
                postDTO.setPosts(postsFounded);
                response.getSites().put(site, postDTO);
              }
        }
        return response;
    }


    private String[][] converSitesToWordsArray(List<Site> sites) {
        String[][] wordsBySite = new String[sites.size()][];

        // Iterate over the sites
        int i = 0;
        for (Site site : sites) {
            // Get the words of the site
            Set<Word> siteWords = site.getWords();

            // Creating an array to store the words
            String[] wordsArray = new String[siteWords.size()];

            // Itering over the words
            int j = 0;
            for (Word word : siteWords) {
                wordsArray[j] = word.getWord();
                j++;
            }

            // Adding the words to the array
            wordsBySite[i] = wordsArray;
            i++;
        }

        return wordsBySite;
    }


}
