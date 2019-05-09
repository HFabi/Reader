package com.example.model.datasources.web

import com.example.model.entities.web.ArticleWebEntity
import com.example.model.models.Article
import com.example.model.transformers.ArticleTransformer
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
class ArticlesWebDataSourceImpl @Inject constructor() : ArticlesWebDataSource {

  @Inject
  lateinit var articleTransformer: ArticleTransformer

  val unencodedHtml1 =
    "<!DOCTYPE html><html><body><div class=\"body__content\"> <p><a href=\"https://github.com/postlight/awesome-cms\">Awesome CMS</a> is…an awesome list of awesome content management systems, grouped by language and ordered by popularity. It’s on GitHub, so anyone can add to it via a pull request.</p>↵<p>Check it out:</p>↵<blockquote class=\"embedly-card\"> <p>\uD83D\uDCDA A collection of open and closed source Content Management Systems (CMS) for your perusal. – postlight/awesome-cms</p>↵</blockquote> <p>Here are some notes on how and why Awesome CMS came to be.</p>↵<h3>Compilation</h3>↵<p>GitHub has a <a href=\"https://help.github.com/articles/search-syntax/\">set of powerful commands</a> for narrowing search results. In seeking out modern content management tools, I used queries like this:</p>↵<p><a href=\"https://github.com/search?o=desc&amp;q=cms+OR+%22content+management%22+OR+admin+pushed%3A%3E2016-01-01+stars%3A%3E50&amp;ref=searchresults&amp;s=stars&amp;type=Repositories&amp;utf8=%E2%9C%93\">cms OR “content management” OR admin pushed:&gt;2016–01–01 stars:&gt;50</a></p>↵<p>Sorting by stars, I worked my way backwards. I was able to quickly spot relevant CMS projects. I also started to notice some trends.</p>↵<ul>↵<li>Modern and popular content management systems are written in PHP, JavaScript, Python, and Ruby. There are also a few content management systems written in&nbsp;.NET (C#), but they are much less popular on GitHub.</li>↵<li>Headless content management systems are gaining popularity. Simply presenting the UI for users to edit content, and relying on the end user to create the user-facing site by ingesting the API. <a href=\"http://getdirectus.com/\">Directus</a> and <a href=\"https://www.cloudcms.com/\">Cloud CMS</a> are headless CMS options.</li>↵<li>Static content management systems don’t host pages for you. Instead they help generate your CMS, using static files. <a href=\"https://github.com/netlify/netlify-cms\">Netlify CMS</a>, <a href=\"https://respondcms.com/\">Respond CMS</a>, and <a href=\"https://www.getlektor.com/\">Lektor</a> are a few of the options in the static CMS space.</li>↵</ul>↵<h3>Tooling</h3>↵<p>I knew the list of all popular content management systems would be huge. I didn’t want to put that data into Markdown directly, as it would be difficult to maintain and to augment with extra data (stars on GitHub, last push date, tags, etc).</p>↵<p>Instead, I opted to store the data in <a href=\"https://github.com/toml-lang/toml\">TOML</a>, a human-friendly configuration file language. You can view all of the data that powers Awesome CMS in the <a href=\"https://github.com/postlight/awesome-cms/tree/97216ef432963d4dfb2238340e2ebf9a4127fb1e/data\">data folder</a>. Here’s WordPress’ entry in that file:</p>↵<pre>[[cms]]↵name = \"WordPress\"↵description = \"WordPress is a free and open-source content management system (CMS) based on PHP and MySQL.\"↵url = \"https://wordpress.org\"↵github_repo = \"WordPress/WordPress\"↵awesome_repo = \"miziomon/awesome-wordpress\"↵language = \"php\"</pre>↵<p>I process this file using JavaScript in <a href=\"https://github.com/postlight/awesome-cms/blob/97216ef432963d4dfb2238340e2ebf9a4127fb1e/scripts/generateReadme.js\">generateReadme.js</a>. It handles processing the TOML, fetching information from GitHub, and generating the final README.md file using the <a href=\"https://github.com/postlight/awesome-cms/blob/master/README.md.hbs\">Handlebars template</a>. I’m scraping GitHub for star counts because GitHub’s API only allows for 60 requests an hour for authenticated users. We want to make it as easy as possible for anyone to contribute. Requiring users to generate a GitHub authentication token to generate the README wasn’t an option.</p>↵<div id=\"attachment_2200\" class=\"wp-caption alignnone\"><img class=\"wp-image-2200 size-full\" src=\"https://13c27d41k2ud2vkddp226w55-wpengine.netdna-ssl.com/wp-content/uploads/2016/10/03-building-awesome-cms-1.gif\" width=\"360\"><p class=\"wp-caption-text\">I heard you like content management systems</p></div>↵<p>By storing the data in TOML at generating the README.md using JavaScript, I’ve essentially created an incredibly light-weight, GitHub-backed, static CMS – to power Awesome CMS.</p>↵<p><em><a href=\"https://postlight.com/trackchanges/author/jeremy-mack\">Jeremy Mack</a> is a Director of Engineering at Postlight. Need a better CMS? Get in touch: <a href=\"https://trackchanges.postlight.com/cdn-cgi/l/email-protection#7b131e1717143b0b14080f17121c130f55181416\"><span class=\"__cf_email__\">[email&nbsp;protected]</span></a>.</em></p> </body></div></html>"
  val article1 =
    ArticleWebEntity(
      id = 1,
      author = "Jeremy 5",
      title = "Building Awesome CMS — Postlight — Digital product studio",
      content = unencodedHtml1,
      domain = "trackchanges.postlight.com",
      url = "https://trackchanges.postlight.com/building-awesome-cms-f034344d8ed",
      excerpt = "Awesome CMS is…an awesome list of awesome content management systems, grouped by language and ordered by popularity. It’s on GitHub, so anyone can add to ...",
      leadImageUrl = "https://postlight.com/wp-content/uploads/2016/10/03-building-awesome-cms-0.png",
      nextPageUrl = "",
      addedAt = Date()
    )

  val unencodedHtml2 =
    "<div class=\"body__content\"> <p><a href=\"https://github.com/postlight/awesome-cms\">Awesome CMS</a> is…an awesome list of awesome content management systems, grouped by language and ordered by popularity. It’s on GitHub, so anyone can add to it via a pull request.</p>↵<p>Check it out:</p>↵<blockquote class=\"embedly-card\"> <p>\uD83D\uDCDA A collection of open and closed source Content Management Systems (CMS) for your perusal. – postlight/awesome-cms</p>↵</blockquote> <p>Here are some notes on how and why Awesome CMS came to be.</p>↵<h3>Compilation</h3>↵<p>GitHub has a <a href=\"https://help.github.com/articles/search-syntax/\">set of powerful commands</a> for narrowing search results. In seeking out modern content management tools, I used queries like this:</p>↵<p><a href=\"https://github.com/search?o=desc&amp;q=cms+OR+%22content+management%22+OR+admin+pushed%3A%3E2016-01-01+stars%3A%3E50&amp;ref=searchresults&amp;s=stars&amp;type=Repositories&amp;utf8=%E2%9C%93\">cms OR “content management” OR admin pushed:&gt;2016–01–01 stars:&gt;50</a></p>↵<p>Sorting by stars, I worked my way backwards. I was able to quickly spot relevant CMS projects. I also started to notice some trends.</p>↵<ul>↵<li>Modern and popular content management systems are written in PHP, JavaScript, Python, and Ruby. There are also a few content management systems written in&nbsp;.NET (C#), but they are much less popular on GitHub.</li>↵<li>Headless content management systems are gaining popularity. Simply presenting the UI for users to edit content, and relying on the end user to create the user-facing site by ingesting the API. <a href=\"http://getdirectus.com/\">Directus</a> and <a href=\"https://www.cloudcms.com/\">Cloud CMS</a> are headless CMS options.</li>↵<li>Static content management systems don’t host pages for you. Instead they help generate your CMS, using static files. <a href=\"https://github.com/netlify/netlify-cms\">Netlify CMS</a>, <a href=\"https://respondcms.com/\">Respond CMS</a>, and <a href=\"https://www.getlektor.com/\">Lektor</a> are a few of the options in the static CMS space.</li>↵</ul>↵<h3>Tooling</h3>↵<p>I knew the list of all popular content management systems would be huge. I didn’t want to put that data into Markdown directly, as it would be difficult to maintain and to augment with extra data (stars on GitHub, last push date, tags, etc).</p>↵<p>Instead, I opted to store the data in <a href=\"https://github.com/toml-lang/toml\">TOML</a>, a human-friendly configuration file language. You can view all of the data that powers Awesome CMS in the <a href=\"https://github.com/postlight/awesome-cms/tree/97216ef432963d4dfb2238340e2ebf9a4127fb1e/data\">data folder</a>. Here’s WordPress’ entry in that file:</p>↵<pre>[[cms]]↵name = \"WordPress\"↵description = \"WordPress is a free and open-source content management system (CMS) based on PHP and MySQL.\"↵url = \"https://wordpress.org\"↵github_repo = \"WordPress/WordPress\"↵awesome_repo = \"miziomon/awesome-wordpress\"↵language = \"php\"</pre>↵<p>I process this file using JavaScript in <a href=\"https://github.com/postlight/awesome-cms/blob/97216ef432963d4dfb2238340e2ebf9a4127fb1e/scripts/generateReadme.js\">generateReadme.js</a>. It handles processing the TOML, fetching information from GitHub, and generating the final README.md file using the <a href=\"https://github.com/postlight/awesome-cms/blob/master/README.md.hbs\">Handlebars template</a>. I’m scraping GitHub for star counts because GitHub’s API only allows for 60 requests an hour for authenticated users. We want to make it as easy as possible for anyone to contribute. Requiring users to generate a GitHub authentication token to generate the README wasn’t an option.</p>↵<div id=\"attachment_2200\" class=\"wp-caption alignnone\"><img class=\"wp-image-2200 size-full\" src=\"https://13c27d41k2ud2vkddp226w55-wpengine.netdna-ssl.com/wp-content/uploads/2016/10/03-building-awesome-cms-1.gif\" width=\"360\"><p class=\"wp-caption-text\">I heard you like content management systems</p></div>↵<p>By storing the data in TOML at generating the README.md using JavaScript, I’ve essentially created an incredibly light-weight, GitHub-backed, static CMS – to power Awesome CMS.</p>↵<p><em><a href=\"https://postlight.com/trackchanges/author/jeremy-mack\">Jeremy Mack</a> is a Director of Engineering at Postlight. Need a better CMS? Get in touch: <a href=\"https://trackchanges.postlight.com/cdn-cgi/l/email-protection#7b131e1717143b0b14080f17121c130f55181416\"><span class=\"__cf_email__\">[email&nbsp;protected]</span></a>.</em></p> </div>"
  val article2 =
    ArticleWebEntity(
      id = 2,
      author = "Jeremy 5",
      title = "Building Awesome CMS — Postlight — Digital product studio",
      content = unencodedHtml2,
      domain = "trackchanges.postlight.com",
      url = "https://trackchanges.postlight.com/building-awesome-cms-f034344d8ed",
      excerpt = "Awesome CMS is…an awesome list of awesome content management systems, grouped by language and ordered by popularity. It’s on GitHub, so anyone can add to ...",
      leadImageUrl = "https://postlight.com/wp-content/uploads/2016/10/03-building-awesome-cms-0.png",
      nextPageUrl = "",
      addedAt = Date()
    )

  override fun parseHtmlFromUrl(url: String): Single<Article> {
    return Single.just(article1)
      .map { articleWebEntity -> articleTransformer.toModel(articleWebEntity) }
  }

//  override fun parseHtmlFromUrl(url: String): Single<Article> {
//    return Single.create { emitter ->
//      emitter.onSuccess()
//    }.map { articleWebEntity -> articleTransformer.toModel(articleWebEntity) }
//  }

}