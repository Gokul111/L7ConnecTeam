package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import com.l7.connecteam.dao.TrainingGroupDao;
import com.l7.connecteam.dto.RoleDto;
import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author Litmus7
 * This class implements the DAO class for training group
 */
public class TrainingGroupDaoImpl implements TrainingGroupDao {
	//private static final Logger LOGGER = LogManager.getLogger(TrainingGroupDaoImpl.class);

	/**
	 * Checks if a training group exists in DB or not
	 * @param trainDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	@Override
	public TrainingGroupDto ifTrainingGroupExists(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifTrainingGroupExists");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, trainDataObj.getTrainGroupName());
			rs = stmt.executeQuery();
			if (rs.next()) {
				trainDataObj.setTrainGroupID(rs.getInt("training_group_id"));
				trainDataObj.setTrainGroupName(rs.getString("training_group_name"));
				trainDataObj.setTrainStartDate(rs.getDate("start_date"));
				trainDataObj.setTrainEndDate(rs.getDate("end_date"));
				trainDataObj.setCoursePlanPath(rs.getString("course_plan_path"));
				trainDataObj.setTypeID(rs.getInt("type_id"));
				trainDataObj.setActiveStatus(rs.getInt("active_status"));
			} else {
				System.out.println(trainDataObj.getTrainGroupID());
				return trainDataObj;
			}
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return trainDataObj;
	}
	public int ifTrainingGroupNameExists(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int trainingGroupId=0;

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifTrainingGroupExists");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, trainDataObj.getTrainGroupName());
			rs = stmt.executeQuery();
			if(rs.next())
				trainingGroupId=rs.getInt(1);
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return trainingGroupId;
	}

	/**
	 * Writes a training group's data to DB if not already exists
	 * @param trainDataObj
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	@Override
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj, UserDto userDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int trainTypeID = 0;
		int ifCreated = 0;
		final String coursePlan = "Plan path details";

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("createTrainingGrp");
			trainTypeID = getTrainTypeID();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, trainDataObj.getTrainGroupName());
			stmt.setDate(2, trainDataObj.getTrainStartDate());
			stmt.setDate(3, trainDataObj.getTrainEndDate());
			stmt.setString(4, coursePlan);
			stmt.setInt(5, trainTypeID);
			ifCreated = stmt.executeUpdate();
			if (ifCreated == 1) {
				//LOGGER.info("Training group " + trainDataObj.getTrainGroupName() + "created");
			} else {
				//LOGGER.info("Failed to create training group" + trainDataObj.getTrainGroupName());
			}
			trainDataObj = ifTrainingGroupExists(trainDataObj);
			Boolean isRelSet = setUserTrainingRel(userDataObj, trainDataObj.getTrainGroupID());
			if (isRelSet == true) {
				//LOGGER.info("User training group relation created");
			} else {
				//LOGGER.info("User training group relation creation failed");
			}

		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return trainDataObj;
	}

	/**
	 * Sets relation between user and respective training group in DB
	 * @param userDataObj
	 * @param trainID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	@Override
	public boolean setUserTrainingRel(UserDto userDataObj, int trainID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt=null;
		int ifCreated = 0;
		int roleID = 0;
		final int statusID=1;

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("setUserTrainingGrpRel");
			stmt = con.prepareStatement(sql);
			List<RoleDto> roleList = userDataObj.getRoleList();
			for (RoleDto roleDto : roleList) {
				roleID = roleDto.getRoleId();
			}
			boolean ifRelExists = ifUserTrainRelExists(userDataObj.getUserId(), trainID);
			if (ifRelExists == false) {
				stmt.setInt(1, trainID);
				stmt.setInt(2, userDataObj.getUserId());
				stmt.setInt(3, roleID);
				stmt.setInt(4, statusID);
				ifCreated = stmt.executeUpdate();
			}
			else {
				//LOGGER.info("UserDto training group relation already exists");
			}
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		boolean setSuccess = ifCreated == 1 ? true : false;
		return setSuccess;
	}

	/**
	 * Checks if user training group relation exists in DB or not
	 * @param userID
	 * @param trainID
	 * @return
	 * @throws UIException
	 * @throws SQLException 
	 */
	@Override
	public boolean ifUserTrainRelExists(int userID, int trainID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean ifRelExists = false;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifUserTrainRelExists");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, trainID);
			stmt.setInt(2, userID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				ifRelExists = true;
			}
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return ifRelExists;
	}

	public boolean ifUserTrainingRelExists(int trainID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean ifRelExists = false;

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifuserTrainingRelExists");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, trainID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				ifRelExists = true;
			}
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return ifRelExists;
	}
	public boolean ifTechnologyTrainingRelExists(int trainID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean ifRelExists = false;

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifTrainingTechnologyRelExists");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, trainID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				ifRelExists = true;
			}
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return ifRelExists;
	}


	/**
	 * Returns the training type ID for a training
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	@Override
	public int getTrainTypeID() throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int trainTypeID = 0;

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getTrainTypeID");
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				trainTypeID = rs.getInt("type_id");
			}
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}

		}
		return trainTypeID;
	}

	@Override
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int trainTypeID = 1;
		int ifCreated = 0;

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("createTrainingGrp");
			int exist = ifTrainingGroupNameExists(trainDataObj);
			if(exist!=0)
				System.out.println("Already Exists");
			else {
				trainTypeID = getTrainTypeID(trainDataObj.getTrainType());
				stmt = con.prepareStatement(sql);

				stmt.setString(1, trainDataObj.getTrainGroupName());
				stmt.setDate(2, trainDataObj.getTrainStartDate());
				stmt.setDate(3, trainDataObj.getTrainEndDate());
				stmt.setString(4, trainDataObj.getCoursePlanPath());
				stmt.setInt(5,trainTypeID );

				ifCreated = stmt.executeUpdate();
				System.out.println("Created");
			}

			if (ifCreated == 1) {
				//LOGGER.info("Training group " + trainDataObj.getTrainGroupName() + "created");
			} else {
				//LOGGER.info("Failed to create training group" + trainDataObj.getTrainGroupName());
			}



		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return trainDataObj;

	}

	@Override
	public TrainingGroupDto updateTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException {

		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int trainTypeID = 1;
		int ifCreated = 0;
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("updateTrainingGroup");

			stmt = con.prepareStatement(sql);
			trainTypeID = ifTrainingGroupNameExists(trainDataObj);
			if(trainTypeID==getTrainTypeID(trainDataObj.getTrainType()))
				System.out.println("Already Exists");
			else
			{
				trainTypeID = getTrainTypeID(trainDataObj.getTrainType());
				stmt.setString(1, trainDataObj.getTrainGroupName());
				stmt.setDate(2, trainDataObj.getTrainStartDate());
				stmt.setDate(3, trainDataObj.getTrainEndDate());
				stmt.setString(4, trainDataObj.getCoursePlanPath());
				stmt.setInt(5, trainTypeID);
				stmt.setInt(6, trainDataObj.getTrainGroupID());
				ifCreated = stmt.executeUpdate();

			}

			if (ifCreated == 1) {
				System.out.println("updated");
				//LOGGER.info("Training group " + trainDataObj.getTrainGroupName() + "updated");
			} else {
				//LOGGER.info("Failed to update training group" + trainDataObj.getTrainGroupName());
			}


		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return trainDataObj;
	}


	public boolean deleteTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean state=false;
		int ifdeleted=0;
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("deleteTrainingGroup");
			stmt = con.prepareStatement(sql);
			int trainID=trainDataObj.getTrainGroupID();
			stmt.setInt(1, trainID);
			if(ifUserTrainingRelExists(trainID)|ifTechnologyTrainingRelExists(trainID)) {
				System.out.println("Relation exists!!! Deletion cannot be done");
			}else {
				ifdeleted=stmt.executeUpdate();
			}
			if(ifdeleted!=0) {
				System.out.println("deletion occurs");
				state=true;
			}



		}catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}

		return state;
	}

	@Override
	public List<TrainingGroupDto> viewAllTrainingGroup() throws UIException, SQLException {

		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ifCreated = 0;
		List<TrainingGroupDto> trainGroupObj=new ArrayList<TrainingGroupDto>();

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getAllTrainingGroup");

			stmt = con.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				ifCreated=1;
				TrainingGroupDto trainDataObj=new TrainingGroupDto();
				trainDataObj.setTrainGroupID(rs.getInt(1));
				trainDataObj.setTrainGroupName(rs.getString(2));
				trainDataObj.setTrainStartDate(rs.getDate(3));
				trainDataObj.setTrainEndDate(rs.getDate(4));
				trainDataObj.setCoursePlanPath(rs.getString(5));
				String typeName=getTypeName(rs.getInt(6));
				trainDataObj.setTrainType(typeName);
				trainGroupObj.add(trainDataObj);
			}
			if (ifCreated == 1) {
				//LOGGER.info("Training group " + trainDataObj.getTrainGroupName() + "selected");
			} else {
				//LOGGER.info("Failed to select training group" + trainDataObj.getTrainGroupName());
			}


		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return trainGroupObj;

	}



	@Override
	public TrainingGroupDto viewTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ifCreated = 0;
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getTrainingGroup");

			stmt = con.prepareStatement(sql);
			int trainID=trainDataObj.getTrainGroupID();
			
			stmt.setInt(1, trainID);
			rs=stmt.executeQuery();
			if(rs.next()) {
				ifCreated=1;
				trainDataObj.setTrainGroupID(rs.getInt(1));
				trainDataObj.setTrainGroupName(rs.getString(2));
				trainDataObj.setTrainStartDate(rs.getDate(3));
				trainDataObj.setTrainEndDate(rs.getDate(4));
				trainDataObj.setCoursePlanPath(rs.getString(5));
				String trainTypeName=getTypeName(rs.getInt(6));
				trainDataObj.setTrainType(trainTypeName);
			
			}
			if (ifCreated == 1) {
				//LOGGER.info("Training group " + trainDataObj.getTrainGroupName() + "selected");
			} else {
				//LOGGER.info("Failed to select training group" + trainDataObj.getTrainGroupName());
			}


		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}
		}
		return trainDataObj;
	}
	@Override
	public int getTrainTypeID(String trainType) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int trainTypeID = 0;

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getTrainTypeID");
			stmt = con.prepareStatement(sql);
			stmt.setString(1,trainType);
			rs = stmt.executeQuery();
			while (rs.next()) {
				trainTypeID = rs.getInt("type_id");
			}
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}

		}
		return trainTypeID;
	}
	public String getTypeName(int typeID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String typeName="";

		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getTrainingTypeName");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,typeID);
			rs = stmt.executeQuery();
			if (rs.next()) 
				typeName = rs.getString(1);
			
		} catch (SQLException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Something went wrong",e);
		} catch (DBDownException e) {
			//LOGGER.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					//LOGGER.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable",e);
				}
			}

		}
		return typeName;
	}
}
