// Boost.Geometry (aka GGL, Generic Geometry Library)

// Copyright (c) 2007-2014 Barend Gehrels, Amsterdam, the Netherlands.
// Copyright (c) 2008-2014 Bruno Lalande, Paris, France.
// Copyright (c) 2009-2014 Mateusz Loskot, London, UK.
// Copyright (c) 2013-2014 Adam Wulkiewicz, Lodz, Poland.

<<<<<<< HEAD
// This file was modified by Oracle on 2013-2017.
// Modifications copyright (c) 2013-2017, Oracle and/or its affiliates.
=======
// This file was modified by Oracle on 2013-2019.
// Modifications copyright (c) 2013-2019, Oracle and/or its affiliates.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle
// Contributed and/or modified by Menelaos Karavelas, on behalf of Oracle

// Parts of Boost.Geometry are redesigned from Geodan's Geographic Library
// (geolib/GGL), copyright (c) 1995-2010 Geodan, Amsterdam, the Netherlands.

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_DISJOINT_AREAL_AREAL_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_DISJOINT_AREAL_AREAL_HPP

#include <boost/geometry/core/point_type.hpp>

#include <boost/geometry/algorithms/covered_by.hpp>
#include <boost/geometry/algorithms/detail/for_each_range.hpp>
#include <boost/geometry/algorithms/detail/point_on_border.hpp>

#include <boost/geometry/algorithms/detail/disjoint/linear_linear.hpp>
<<<<<<< HEAD
=======
#include <boost/geometry/algorithms/detail/disjoint/segment_box.hpp>

#include <boost/geometry/iterators/segment_iterator.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce


namespace boost { namespace geometry
{


#ifndef DOXYGEN_NO_DETAIL
namespace detail { namespace disjoint
{

<<<<<<< HEAD

template <typename Geometry, typename Tag = typename tag<Geometry>::type>
struct check_each_ring_for_within_call_covered_by
{
    /*!
    \tparam Strategy point_in_geometry strategy
    */
    template <typename Point, typename Strategy>
    static inline bool apply(Point const& p, Geometry const& g, Strategy const& strategy)
    {
        return geometry::covered_by(p, g, strategy);
    }
};

template <typename Geometry>
struct check_each_ring_for_within_call_covered_by<Geometry, box_tag>
{
    template <typename Point, typename Strategy>
    static inline bool apply(Point const& p, Geometry const& g, Strategy const& )
    {
        return geometry::covered_by(p, g);
    }
};

=======
template <typename Geometry1, typename Geometry2, typename Strategy>
inline bool point_on_border_covered_by(Geometry1 const& geometry1,
                                       Geometry2 const& geometry2,
                                       Strategy const& strategy)
{
    typename geometry::point_type<Geometry1>::type pt;
    return geometry::point_on_border(pt, geometry1)
        && geometry::covered_by(pt, geometry2, strategy);
}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

/*!
\tparam Strategy point_in_geometry strategy
*/
template<typename Geometry, typename Strategy>
struct check_each_ring_for_within
{
    bool not_disjoint;
    Geometry const& m_geometry;
    Strategy const& m_strategy;

    inline check_each_ring_for_within(Geometry const& g,
                                      Strategy const& strategy)
        : not_disjoint(false)
        , m_geometry(g)
        , m_strategy(strategy)
    {}

    template <typename Range>
    inline void apply(Range const& range)
    {
<<<<<<< HEAD
        typename point_type<Range>::type pt;
        not_disjoint = not_disjoint
                || ( geometry::point_on_border(pt, range)
                  && check_each_ring_for_within_call_covered_by
                        <
                            Geometry
                        >::apply(pt, m_geometry, m_strategy) );
=======
        not_disjoint = not_disjoint
                    || point_on_border_covered_by(range, m_geometry, m_strategy);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
};


/*!
\tparam Strategy point_in_geometry strategy
*/
template <typename FirstGeometry, typename SecondGeometry, typename Strategy>
inline bool rings_containing(FirstGeometry const& geometry1,
                             SecondGeometry const& geometry2,
                             Strategy const& strategy)
{
    check_each_ring_for_within
        <
            FirstGeometry, Strategy
        > checker(geometry1, strategy);
    geometry::detail::for_each_range(geometry2, checker);
    return checker.not_disjoint;
}



template <typename Geometry1, typename Geometry2>
<<<<<<< HEAD
struct general_areal
=======
struct areal_areal
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    /*!
    \tparam Strategy relate (segments intersection) strategy
    */
    template <typename Strategy>
    static inline bool apply(Geometry1 const& geometry1,
                             Geometry2 const& geometry2,
                             Strategy const& strategy)
    {
        if ( ! disjoint_linear<Geometry1, Geometry2>::apply(geometry1, geometry2, strategy) )
        {
            return false;
        }

        // If there is no intersection of segments, they might located
        // inside each other

        // We check that using a point on the border (external boundary),
        // and see if that is contained in the other geometry. And vice versa.

        if ( rings_containing(geometry1, geometry2,
                              strategy.template get_point_in_geometry_strategy<Geometry2, Geometry1>())
          || rings_containing(geometry2, geometry1,
                              strategy.template get_point_in_geometry_strategy<Geometry1, Geometry2>()) )
        {
            return false;
        }

        return true;
    }
};


<<<<<<< HEAD
=======
template <typename Areal, typename Box>
struct areal_box
{
    /*!
    \tparam Strategy relate (segments intersection) strategy
    */
    template <typename Strategy>
    static inline bool apply(Areal const& areal,
                             Box const& box,
                             Strategy const& strategy)
    {
        if ( ! for_each_segment(geometry::segments_begin(areal),
                                geometry::segments_end(areal),
                                box,
                                strategy.get_disjoint_segment_box_strategy()) )
        {
            return false;
        }

        // If there is no intersection of any segment and box,
        // the box might be located inside areal geometry

        if ( point_on_border_covered_by(box, areal,
                strategy.template get_point_in_geometry_strategy<Box, Areal>()) )
        {
            return false;
        }

        return true;
    }

private:
    template <typename SegIter, typename Strategy>
    static inline bool for_each_segment(SegIter first,
                                        SegIter last,
                                        Box const& box,
                                        Strategy const& strategy)
    {
        for ( ; first != last ; ++first)
        {
            if (! disjoint_segment_box::apply(*first, box, strategy))
            {
                return false;
            }
        }
        return true;
    }
};


>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}} // namespace detail::disjoint
#endif // DOXYGEN_NO_DETAIL




#ifndef DOXYGEN_NO_DISPATCH
namespace dispatch
{


template <typename Areal1, typename Areal2>
struct disjoint<Areal1, Areal2, 2, areal_tag, areal_tag, false>
<<<<<<< HEAD
    : detail::disjoint::general_areal<Areal1, Areal2>
=======
    : detail::disjoint::areal_areal<Areal1, Areal2>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{};


template <typename Areal, typename Box>
struct disjoint<Areal, Box, 2, areal_tag, box_tag, false>
<<<<<<< HEAD
    : detail::disjoint::general_areal<Areal, Box>
=======
    : detail::disjoint::areal_box<Areal, Box>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{};


} // namespace dispatch
#endif // DOXYGEN_NO_DISPATCH


}} // namespace boost::geometry


#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_DISJOINT_AREAL_AREAL_HPP
