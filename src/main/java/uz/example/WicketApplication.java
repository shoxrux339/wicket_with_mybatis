package uz.example;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.resource.CssUrlReplacer;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see uz.example.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return EditPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		getResourceSettings().setCssCompressor(new CssUrlReplacer());

	/*	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		PersonMapper personMapper = ctx.getBean(PersonMapper.class);
		Person per = new Person();
		per.setId(1);
		per.setAge(24);
		per.setEmail("shoxrux_mts@mail.ru");
		per.setFirstname("shoxrux");
		per.setLastname("Narzullayev");
		personMapper.insertPerson(per);*/

		// add your configuration here
	}
}
