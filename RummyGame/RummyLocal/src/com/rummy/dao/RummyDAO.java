package com.rummy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RummyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Games> findAll() {
		String sql = "SELECT * FROM games";
		List<Games> gameList = 
				jdbcTemplate.query(sql, new BeanPropertyRowMapper<Games>(Games.class));
			return gameList;
	}
	
	public List<Games> getAvailableGame() {
		String sql = "SELECT * FROM games GROUP BY gameid HAVING COUNT(*) = 1";
		List<Games> availGames =
				jdbcTemplate.query(sql, new BeanPropertyRowMapper<Games>(Games.class));
		return availGames;
	}
	
	public List<Stats> getAllStats() {
		String sql = "SELECT username,wins,losses FROM stats LEFT OUTER JOIN users ON stats.userid=users.userid ORDER BY wins DESC, losses DESC";
		List<Stats> statsList =
				jdbcTemplate.query(sql, new BeanPropertyRowMapper<Stats>(Stats.class));
		return statsList;
	}
	
	public List<Audit> getAudit() {
		String sql = "SELECT username,gameid,raction,rcards,rstatus,rmessage FROM audit LEFT OUTER JOIN users ON audit.userid=users.userid";
		List<Audit> auditList =
				jdbcTemplate.query(sql, new BeanPropertyRowMapper<Audit>(Audit.class));
		return auditList;
	}
	
	public List<Integer> getOpenIDs() {
		String sql = "SELECT gameid FROM testrummy.games WHERE win IS NULL GROUP BY gameid  HAVING COUNT(*) = 1";
		List<Integer> openIDs = 
				(List<Integer>)jdbcTemplate.queryForList(sql, Integer.class);
		return openIDs;
	}
	
	public List<Integer> getActiveIDs() {
		String sql = "SELECT gameid FROM testrummy.games WHERE win IS NULL GROUP BY gameid  HAVING COUNT(*) = 2";
		List<Integer> activeIDs =
				(List<Integer>)jdbcTemplate.queryForList(sql, Integer.class);
		return activeIDs;
	}
	
	public List<Integer> getCompleteIDs() {
		String sql = "SELECT gameid FROM testrummy.games WHERE win IS NOT NULL GROUP BY gameid HAVING COUNT(*) = 2";
		List<Integer> completeIDs = 
				(List<Integer>)jdbcTemplate.queryForList(sql, Integer.class);
		return completeIDs;
	}
}
