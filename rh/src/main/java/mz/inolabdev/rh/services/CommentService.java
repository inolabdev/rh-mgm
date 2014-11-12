package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Comment;

public interface CommentService {

	public Comment create(Comment comment);

	public List<Comment> getAll();

	public Comment find(Long id);

	public Comment update(Comment comment);

	public long count();

	public void delete(Object id);

	public Comment first();

	public Comment last();

}
