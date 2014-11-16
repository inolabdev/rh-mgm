package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.ImageDao;
import mz.inolabdev.rh.entity.Image;

import org.springframework.stereotype.Repository;

@Repository("imageDao")
public class ImageDaoImpl extends GenericDaoImpl<Image> implements ImageDao {

}
