const CardHome = ({src,header,description})=>{
    return <>
    <div className="col-md-6 pl-5 pr-5 pb-5">
        <img loading="lazy" src={src} alt={header} width="150"/>
        <h3 className="mt-4 mb-3">{header}</h3>
        <p className="text-muted">{description}</p>
      </div>
    </>
}
export default CardHome ;