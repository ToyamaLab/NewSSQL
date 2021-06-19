// Boost.Geometry

<<<<<<< HEAD
// Copyright (c) 2017 Oracle and/or its affiliates.
=======
// Copyright (c) 2017, 2019 Oracle and/or its affiliates.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_STRATEGY_GEOGRAPHIC_POINT_IN_POLY_WINDING_HPP
#define BOOST_GEOMETRY_STRATEGY_GEOGRAPHIC_POINT_IN_POLY_WINDING_HPP


#include <boost/geometry/strategies/geographic/side.hpp>
#include <boost/geometry/strategies/spherical/point_in_poly_winding.hpp>


namespace boost { namespace geometry
{

namespace strategy { namespace within
{


/*!
\brief Within detection using winding rule in geographic coordinate system.
\ingroup strategies
\tparam Point \tparam_point
\tparam PointOfSegment \tparam_segment_point
\tparam FormulaPolicy Geodesic formula policy
\tparam Spheroid Spheroid model
\tparam CalculationType \tparam_calculation

\qbk{
[heading See also]
[link geometry.reference.algorithms.within.within_3_with_strategy within (with strategy)]
}
 */
template
<
<<<<<<< HEAD
    typename Point,
    typename PointOfSegment = Point,
=======
    typename Point = void, // for backward compatibility
    typename PointOfSegment = Point, // for backward compatibility
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typename FormulaPolicy = strategy::andoyer,
    typename Spheroid = srs::spheroid<double>,
    typename CalculationType = void
>
class geographic_winding
    : public within::detail::spherical_winding_base
        <
<<<<<<< HEAD
            Point,
            PointOfSegment,
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            side::geographic<FormulaPolicy, Spheroid, CalculationType>,
            CalculationType
        >
{
    typedef within::detail::spherical_winding_base
        <
<<<<<<< HEAD
            Point,
            PointOfSegment,
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            side::geographic<FormulaPolicy, Spheroid, CalculationType>,
            CalculationType
        > base_t;

public:
    geographic_winding()
    {}

    explicit geographic_winding(Spheroid const& model)
        : base_t(model)
    {}
<<<<<<< HEAD
=======

    Spheroid const& model() const
    {
        return base_t::m_side_strategy.model();
    }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};


}} // namespace strategy::within


}} // namespace boost::geometry


#endif // BOOST_GEOMETRY_STRATEGY_GEOGRAPHIC_POINT_IN_POLY_WINDING_HPP
