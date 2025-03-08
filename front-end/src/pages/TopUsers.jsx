import TableUsers from "../components/UI/TableUser";
const TopUsers=()=>{
    return(<>
    <div className="container mt-5">
        <div className="row">
                <div className="mb-4 col-lg-6"> <TableUsers/></div>
                <div className="mb-4 col-lg-6"> <TableUsers/></div>
        </div>

    </div>
           
    </>);
}
export default TopUsers;
