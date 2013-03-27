package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import java.io._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */

class ApplicationSpec extends Specification {

	"Application" should {

		"send 404 on a bad request" in {
			running(FakeApplication()) {
				route(FakeRequest(GET, "/boum")) must beNone
			}
		}

		/**
		 * コントローラのテストコード(DB(select,insertなどのtest))
		 */
		"be get all title and body" in {
			running(FakeApplication(additionalConfiguration = inMemoryDatabase("Scala_blog"))) {
				val blog = controllers.Application.blog
				assert(blog != null)
				//blog.doInsert("title", "body")
				val title = blog.getTitles
				val body = blog.getBodies
			}
		}
		/**
		 *  templateのテストコード(index,addpage)
		 */
		"render the index page" in {
			running(FakeApplication()) {
				val home = route(FakeRequest(GET, "/")).get
				contentType(home) must beSome.which(_ == "text/html")
				contentAsString(home) must contain("/")

				val addpage = views.html.addpage("/")
				contentType(addpage) must equalTo("text/html")
				contentAsString(addpage) must contain("/")
			}
		}
	}
}