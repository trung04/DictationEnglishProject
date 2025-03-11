import ToggleDown from "../components/UI/ToggleDown"
import Navbar2 from "../components/Layout/Navbar2";
const ListExercise=()=>{
    return(
        <>
        <Navbar2/>
        <div className="container-lg">
            <div className="mb-4">
                <ToggleDown/>
            </div>
        </div>

        </>
    );
}
export default ListExercise;