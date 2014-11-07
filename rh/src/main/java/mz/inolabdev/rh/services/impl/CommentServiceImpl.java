package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Comment;
import mz.inolabdev.rh.services.CommentService;

import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl extends GenericServiceImpl<Comment> implements
		CommentService {

}
