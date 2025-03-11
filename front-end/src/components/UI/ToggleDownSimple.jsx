const ToggleDownSimple = ({value,onChange}) => {
  return (
    <>
      <select value={value} onChange={(e)=>{onChange(e.target.value);}} className="d-inline w-auto form-select me-2">
        <option value="6">Video size: Small</option>
        <option value="7" selected>
          Video size: Normal
        </option>
        <option value="8">Video size: Large</option>
      </select>
    </>
  );
};
export default ToggleDownSimple;