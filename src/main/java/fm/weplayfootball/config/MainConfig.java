package fm.weplayfootball.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "fm.weplayfootball.web", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:fm/weplayfootball/config/application.properties")
@MapperScan("fm.weplayfootball.persistence.mapper")
@EnableTransactionManagement
public class MainConfig {

	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() {

		BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
		ds.setDriverClassName(env.getProperty("jdbc.driver"));
		ds.setUrl(env.getProperty("jdbc.url"));
		ds.setUsername(env.getProperty("jdbc.username"));
		ds.setPassword(env.getProperty("jdbc.password"));
		/*
		Connection con = null;
		try {
			con = ds.getConnection();
			DatabaseMetaData metaData = con.getMetaData();
			System.out.println("getDatabaseProductName - "+metaData.getDatabaseMajorVersion());
			System.out.println("getDatabaseProductName - "+metaData.getDatabaseProductVersion());
			System.out.println("getDatabaseProductName - "+metaData.getDatabaseProductName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try { 
					con.close();
				} catch (SQLException e) {
					// ignored
				}
			}
		}

		 */
		return ds;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("fm.weplayfootball.persistence.domain");
		return sessionFactory.getObject();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	  public JavaMailSender mailSender() {
	    final JavaMailSenderImpl sender = new JavaMailSenderImpl();
	    sender.setHost(env.getProperty("mail.host"));
	    sender.setUsername(env.getProperty("mail.username"));
	    sender.setPassword(env.getProperty("mail.password"));
	    return sender;
	  }
}
