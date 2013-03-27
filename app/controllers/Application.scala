package controllers

import play.api._
import play.api.mvc._
import play.api.templates
import play.api.mvc.SimpleResult
import play.api.data.Form
import play.api.data.Forms._
import java.util._
import models.BlogComment_BodyDAO

object Application extends Controller {

	/**
	 * initialize parameter
	 * @param blogComment_BodyDAO object
	 * @param str
	 * @param title
	 * @param bodies
	 * @param ids
	 */
	final val blog = new models.BlogComment_BodyDAO()
	final val str = AddPage
	final var titles = new ArrayList[String]
	final var bodies = new ArrayList[String]
	final var ids = new ArrayList[Int]

	/**
	 * @action html
	 */
	def index = Action {
		titles = blog.getTitles
		bodies = blog.getBodies
		ids = blog.getBlog_id
		Ok(views.html.index(titles, bodies, ids, 1))
	}

	/**
	 * @form url,comment,id
	 * @action html
	 */
	def postComment = Action { implicit request ⇒
		val url = request.body.asFormUrlEncoded.get("url").toString()
		val comment = request.body.asFormUrlEncoded.get("comment").toString()
		val id = request.body.asFormUrlEncoded.get("id").toString()
		var message : String = null
		val split_url = str.split(url)
		val split_com = str.split(comment)
		val split_id = str.split(id).toInt
		if (split_com.length() != 0) {
			blog.doInsert(split_url, split_com, split_id)
		} else {
			message = "コメントが入力されていません"
		}
		Ok(views.html.index(titles, bodies, ids, 1))
	}

	/**
	 * @param id :Integer
	 * @action html
	 */
	def page(id : Int) = Action {
		titles = blog.getTitles
		bodies = blog.getBodies
		ids = blog.getBlog_id
		Ok(views.html.index(titles, bodies, ids, id))
	}
}
