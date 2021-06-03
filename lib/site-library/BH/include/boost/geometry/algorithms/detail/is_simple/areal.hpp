// Boost.Geometry (aka GGL, Generic Geometry Library)

<<<<<<< HEAD
// Copyright (c) 2014-2017, Oracle and/or its affiliates.
=======
// Copyright (c) 2014-2019, Oracle and/or its affiliates.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

// Contributed and/or modified by Menelaos Karavelas, on behalf of Oracle
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

// Licensed under the Boost Software License version 1.0.
// http://www.boost.org/users/license.html

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_IS_SIMPLE_AREAL_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_IS_SIMPLE_AREAL_HPP

#include <boost/range.hpp>

#include <boost/geometry/core/closure.hpp>
#include <boost/geometry/core/exterior_ring.hpp>
#include <boost/geometry/core/interior_rings.hpp>
#include <boost/geometry/core/ring_type.hpp>
#include <boost/geometry/core/tags.hpp>

#include <boost/geometry/algorithms/detail/check_iterator_range.hpp>
#include <boost/geometry/algorithms/detail/is_simple/failure_policy.hpp>
#include <boost/geometry/algorithms/detail/is_valid/has_duplicates.hpp>

#include <boost/geometry/algorithms/dispatch/is_simple.hpp>


namespace boost { namespace geometry
{


#ifndef DOXYGEN_NO_DETAIL
namespace detail { namespace is_simple
{


<<<<<<< HEAD
template <typename Ring>
struct is_simple_ring
{
    template <typename Strategy>
    static inline bool apply(Ring const& ring, Strategy const&)
    {
        return apply(ring);
    }

=======
template <typename Ring, typename CSTag>
struct is_simple_ring
{
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static inline bool apply(Ring const& ring)
    {
        simplicity_failure_policy policy;
        return ! boost::empty(ring)
            && ! detail::is_valid::has_duplicates
                    <
<<<<<<< HEAD
                        Ring, geometry::closure<Ring>::value
=======
                        Ring, geometry::closure<Ring>::value, CSTag
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                    >::apply(ring, policy);
    }
};


<<<<<<< HEAD
template <typename Polygon>
=======
template <typename Polygon, typename CSTag>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
class is_simple_polygon
{
private:
    template <typename InteriorRings>
    static inline
    bool are_simple_interior_rings(InteriorRings const& interior_rings)
    {
        return
            detail::check_iterator_range
                <
                    is_simple_ring
                        <
<<<<<<< HEAD
                            typename boost::range_value<InteriorRings>::type
=======
                            typename boost::range_value<InteriorRings>::type,
                            CSTag
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                        >
                >::apply(boost::begin(interior_rings),
                         boost::end(interior_rings));
    }

public:
<<<<<<< HEAD
    template <typename Strategy>
    static inline bool apply(Polygon const& polygon, Strategy const&)
    {
        return apply(polygon);
    }

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static inline bool apply(Polygon const& polygon)
    {
        return
            is_simple_ring
                <
<<<<<<< HEAD
                    typename ring_type<Polygon>::type
=======
                    typename ring_type<Polygon>::type,
                    CSTag
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                >::apply(exterior_ring(polygon))
            &&
            are_simple_interior_rings(geometry::interior_rings(polygon));
    }
};


}} // namespace detail::is_simple
#endif // DOXYGEN_NO_DETAIL




#ifndef DOXYGEN_NO_DISPATCH
namespace dispatch
{


// A Ring is a Polygon.
// A Polygon is always a simple geometric object provided that it is valid.
//
// Reference (for polygon validity): OGC 06-103r4 (6.1.11.1)
template <typename Ring>
struct is_simple<Ring, ring_tag>
<<<<<<< HEAD
    : detail::is_simple::is_simple_ring<Ring>
{};
=======
{
    template <typename Strategy>
    static inline bool apply(Ring const& ring, Strategy const&)
    {
        return detail::is_simple::is_simple_ring
            <
                Ring,
                typename Strategy::cs_tag
            >::apply(ring);
    }
};
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce


// A Polygon is always a simple geometric object provided that it is valid.
//
// Reference (for validity of Polygons): OGC 06-103r4 (6.1.11.1)
template <typename Polygon>
struct is_simple<Polygon, polygon_tag>
<<<<<<< HEAD
    : detail::is_simple::is_simple_polygon<Polygon>
{};
=======
{
    template <typename Strategy>
    static inline bool apply(Polygon const& polygon, Strategy const&)
    {
        return detail::is_simple::is_simple_polygon
            <
                Polygon,
                typename Strategy::cs_tag
            >::apply(polygon);
    }
};
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce


// Not clear what the definition is.
// Right now we consider a MultiPolygon as simple if it is valid.
//
// Reference (for validity of MultiPolygons): OGC 06-103r4 (6.1.14)
template <typename MultiPolygon>
struct is_simple<MultiPolygon, multi_polygon_tag>
{
    template <typename Strategy>
    static inline bool apply(MultiPolygon const& multipolygon, Strategy const&)
    {
        return
            detail::check_iterator_range
                <
                    detail::is_simple::is_simple_polygon
                        <
<<<<<<< HEAD
                            typename boost::range_value<MultiPolygon>::type
=======
                            typename boost::range_value<MultiPolygon>::type,
                            typename Strategy::cs_tag
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                        >,
                    true // allow empty multi-polygon
                >::apply(boost::begin(multipolygon), boost::end(multipolygon));
    }
};


} // namespace dispatch
#endif // DOXYGEN_NO_DISPATCH


}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_IS_SIMPLE_AREAL_HPP
