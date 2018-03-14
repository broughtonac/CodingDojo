package com.broughton.dojooverflow.services;

import java.util.*;
import org.springframework.stereotype.Service;
import com.broughton.dojooverflow.models.*;
import com.broughton.dojooverflow.repositories.*;

@Service
public class DojoOverflowService {
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;
	private TagRepository tagRepository;
	public DojoOverflowService(
		QuestionRepository questionRepository,
		AnswerRepository answerRepository,
		TagRepository tagRepository) {
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
		this.tagRepository = tagRepository;
	}
	// question services
	public void createQuestion(Question question) {
		questionRepository.save(question);
	}
	public Question findQuestion(Long id) {
		return questionRepository.findOne(id);
	}
	public List<Question> findAllQuestions() {
		return (List<Question>) questionRepository.findAll();
	}
	// answer services
	public void createAndBindAnswer(Question question, Answer answer) {
		if (question.getAnswers() == null) {
			question.setAnswers(new ArrayList<Answer>());
		}
		answer.setQuestion(question);
		answerRepository.save(answer);
		question.getAnswers().add(answer);
	}
	// tag services
	public void createAndBindTags(Question question, String tags) {
		if (question.getTags() == null) {
			question.setTags(new ArrayList<Tag>());
		}
		List<Tag> questionTags = question.getTags();
		for (String subject : Arrays.asList(tags.split("\\s*,\\s*"))) {
			Tag tag;
			if (!(tagSubjectExists(subject))) {
				tag = new Tag();
				tag.setSubject(subject);
				tagRepository.save(tag);
			}
			else {
				tag = this.findTag(tagRepository.getTagIdBySubject(subject));
			}
			if (!(questionTags.contains(tag))) {
				questionTags.add(tag);
			}
		}
		question.setTags(questionTags);
		questionRepository.save(question);
	}
	public Tag findTag(Long tagId) {
		return tagRepository.findOne(tagId);
	}
	public Boolean tagSubjectExists(String subject) {
		if (tagRepository.countTagsWithName(subject) == 0) {
			return false;
		}
		else {
			return true;
		}
	}
}
