package controllers

import play.api._
import play.api.mvc._
import java.util._
import java.lang._

object AddPage extends Controller {

	final val blog = Application.blog

	def index = Action {
		Ok(views.html.addpage(null))
	}
	def postBlog = Action { implicit request ⇒

		val title = request.body.asFormUrlEncoded.get("title").toString()
		val body = request.body.asFormUrlEncoded.get("body").toString()
		var message : String = null
		val split_title = split(title)
		val split_body = split(body)
		if (split_title.length() != 0 &&
			split_body.length() != 0) {
			blog.doInsert(split_title, split_body)
		} else {
			message = "タイトル、本文を入力してください"
		}
		Ok(views.html.addpage(message))
	}
	def split(data : String) : String = {

		var split : String = null
		var start = data.indexOf("(")
		var end = data.indexOf(")")
		split = data.substring(start + 1, end)
		return split
	}
}