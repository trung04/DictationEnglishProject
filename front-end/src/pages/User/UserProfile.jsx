import { Link } from "react-router-dom";

const UserProfile = ({ userData }) => {
  console.log(userData);
  return (
    <>
      <main className="container-lg">
        <div className="message"></div>

        <h2>Account information</h2>
        <table className="table table-bordered">
          <tbody>
            <tr>
              <th>Email</th>
              <td>{userData.email}</td>
            </tr>
            <tr>
              <th>Display name</th>
              <td className="d-flex align-items-center">{userData.username}</td>
            </tr>

            <tr>
              <th>Join date</th>
              <td>{userData.createdAt}</td>
            </tr>

            <tr>
              <th>Active hours</th>
              <td>{userData.activeHours}</td>
            </tr>
            <tr>
              <th>Active days</th>
              <td>{userData.activeDays}</td>
            </tr>
          </tbody>
        </table>
      </main>
    </>
  );
};
export default UserProfile;
