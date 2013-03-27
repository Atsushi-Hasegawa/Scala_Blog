package models
import java.util._
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.PreparedStatement
import java.sql.Connection
import play.db.DB

class BlogComment_BodyDAO {

	/**
	 * initialize parameter
	 * @param connection
	 * @param statement
	 */
	final val connect = DB.getConnection()
	final val stmt = connect.createStatement()

	/**
	 *  @param nothing
	 *  @return title: ArrayList[String]
	 */
	def getTitles : ArrayList[String] = {

		val title = new ArrayList[String]
		val sql = "SELECT title from blog"
		try {
			val result : ResultSet = stmt.executeQuery(sql)
			while (result.next) {
				val line = result.getString(1)
				title.add(line)
			}
			result.close()
		} catch {
			case e : SQLException ⇒ println("Database error: " + e.getClass().getName())
		}
		return title
	}

	/**
	 *  @param nothing
	 *  @return body: ArrayList[String]
	 */
	def getBodies : ArrayList[String] = {

		val body = new ArrayList[String]
		val sql = "SELECT body from blog"
		try {
			val result = stmt.executeQuery(sql)
			while (result.next) {
				val line = result.getString(1)
				body.add(line)
			}
			result.close()
		} catch {
			case e : SQLException ⇒ println("Database error: " + e.getClass().getName())
		}
		return body
	}
	/**
	 *  @param nothing
	 *  @return id: ArrayList[Integer]
	 */
	def getBlog_id : ArrayList[Int] = {

		val id = new ArrayList[Int]
		val sql = "SELECT id from blog"
		try {
			val result = stmt.executeQuery(sql)
			while (result.next) {
				val number = result.getInt(1)
				id.add(number)
			}
			result.close()
		} catch {
			case e : SQLException ⇒ println("Database error: " + e.getClass().getName());
		}
		return id
	}

	/**
	 * @param title: String
	 * @param body: String
	 */
	def doInsert(title : String, body : String) = {

		val sql : String = "INSERT INTO blog(title,body)" + "VALUES(?,?)"
		var prestmt : PreparedStatement = null
		try {
			prestmt = connect.prepareStatement(sql)
			prestmt.setString(1, title)
			prestmt.setString(2, body)
			val num = prestmt.executeUpdate()
			prestmt.close()
		} catch {
			case e : SQLException ⇒ println("Database error: " + e.getClass().getName())
		}
	}

	/**
	 *  @param nothing
	 *  @return comments: ArrayList[String]
	 */
	def getComments : ArrayList[String] = {

		val comments = new ArrayList[String]
		val sql = "SELECT comment from blog blog_comment"
		val result : ResultSet = stmt.executeQuery(sql)
		try {
			while (result.next) {
				val line = result.getString(1)
				comments.add(line)
			}
			result.close()
		} catch {
			case e : SQLException ⇒ println("Database error" + e.getClass.getName());
		}
		return comments
	}

	/**
	 *  @param nothing
	 *  @return url: ArrayList[String]
	 */
	def getUrls : ArrayList[String] = {

		val url = new ArrayList[String]
		val sql = "SELECT url from blog_comment"
		try {
			val result : ResultSet = stmt.executeQuery(sql)
			while (result.next) {
				val line = result.getString(1)
				url.add(line)
			}
			result.close()
		} catch {
			case e : SQLException ⇒ println("Database error" + e.getClass().getName());
		}
		return url
	}

	/**
	 *  @param nothing
	 *  @return id: ArrayList[Integer]
	 */
	def getIds : ArrayList[Int] = {

		val id = new ArrayList[Int]
		val sql = "SELECT blog_id from blog_comment"
		try {
			val result : ResultSet = stmt.executeQuery(sql)
			while (result.next) {
				val number = result.getInt(1)
				id.add(number)
			}
			result.close()
		} catch {
			case e : SQLException ⇒ println("Database error" + e.getClass().getName())
		}
		return id
	}

	/**
	 * @override
	 * @param url: String
	 * @param comment: String
	 * @param blog_id: Integer
	 */
	def doInsert(url : String, comment : String, blog_id : Int) {

		val sql = "INSERT INTO blog_comment(url,comment,blog_id) VALUES(?,?,?)"
		var prestmt : PreparedStatement = null
		try {
			prestmt = connect.prepareStatement(sql)
			prestmt.setString(1, url)
			prestmt.setString(2, comment)
			prestmt.setInt(3, blog_id)
			val num = prestmt.executeUpdate()
			prestmt.close()
		} catch {
			case e : SQLException ⇒ println("Database error" + e.getClass().getName())
		}
	}
}