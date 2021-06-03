// Boost.Geometry (aka GGL, Generic Geometry Library)

<<<<<<< HEAD
// Copyright (c) 2014 Oracle and/or its affiliates.
=======
// Copyright (c) 2014-2018 Oracle and/or its affiliates.

// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

<<<<<<< HEAD
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_RELATE_BOUNDARY_CHECKER_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_RELATE_BOUNDARY_CHECKER_HPP

#include <boost/geometry/util/range.hpp>
#include <boost/geometry/algorithms/num_points.hpp>
#include <boost/geometry/algorithms/detail/sub_range.hpp>

#include <boost/geometry/algorithms/detail/equals/point_point.hpp>

#include <boost/geometry/util/has_nan_coordinate.hpp>
=======
#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_RELATE_BOUNDARY_CHECKER_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_RELATE_BOUNDARY_CHECKER_HPP

#include <boost/core/ignore_unused.hpp>

#include <boost/geometry/algorithms/detail/equals/point_point.hpp>
#include <boost/geometry/algorithms/detail/sub_range.hpp>
#include <boost/geometry/algorithms/num_points.hpp>

#include <boost/geometry/policies/compare.hpp>

#include <boost/geometry/util/has_nan_coordinate.hpp>
#include <boost/geometry/util/range.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost { namespace geometry
{

#ifndef DOXYGEN_NO_DETAIL
namespace detail { namespace relate {

enum boundary_query { boundary_front, boundary_back, boundary_any };

template <typename Geometry,
<<<<<<< HEAD
          typename Tag = typename geometry::tag<Geometry>::type>
class boundary_checker {};

template <typename Geometry>
class boundary_checker<Geometry, linestring_tag>
=======
          typename WithinStrategy, // Point/Point equals (within) strategy
          typename Tag = typename geometry::tag<Geometry>::type>
class boundary_checker {};

template <typename Geometry, typename WithinStrategy>
class boundary_checker<Geometry, WithinStrategy, linestring_tag>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    typedef typename point_type<Geometry>::type point_type;

public:
<<<<<<< HEAD
    boundary_checker(Geometry const& g)
        : has_boundary( boost::size(g) >= 2
                     && !detail::equals::equals_point_point(range::front(g), range::back(g)) )
        , geometry(g)
=======
    typedef WithinStrategy equals_strategy_type;

    boundary_checker(Geometry const& g)
        : has_boundary( boost::size(g) >= 2
                     && !detail::equals::equals_point_point(range::front(g),
                                                            range::back(g),
                                                            equals_strategy_type()) )
#ifdef BOOST_GEOMETRY_DEBUG_RELATE_BOUNDARY_CHECKER
        , geometry(g)
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {}

    template <boundary_query BoundaryQuery>
    bool is_endpoint_boundary(point_type const& pt) const
    {
<<<<<<< HEAD
        boost::ignore_unused_variable_warning(pt);
#ifdef BOOST_GEOMETRY_DEBUG_RELATE_BOUNDARY_CHECKER
        // may give false positives for INT
        BOOST_GEOMETRY_ASSERT( (BoundaryQuery == boundary_front || BoundaryQuery == boundary_any)
                   && detail::equals::equals_point_point(pt, range::front(geometry))
                   || (BoundaryQuery == boundary_back || BoundaryQuery == boundary_any)
                   && detail::equals::equals_point_point(pt, range::back(geometry)) );
=======
        boost::ignore_unused(pt);
#ifdef BOOST_GEOMETRY_DEBUG_RELATE_BOUNDARY_CHECKER
        // may give false positives for INT
        BOOST_GEOMETRY_ASSERT( (BoundaryQuery == boundary_front || BoundaryQuery == boundary_any)
                   && detail::equals::equals_point_point(pt, range::front(geometry), WithinStrategy())
                   || (BoundaryQuery == boundary_back || BoundaryQuery == boundary_any)
                   && detail::equals::equals_point_point(pt, range::back(geometry), WithinStrategy()) );
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif
        return has_boundary;
    }

private:
    bool has_boundary;
<<<<<<< HEAD
    Geometry const& geometry;
};

template <typename Geometry>
class boundary_checker<Geometry, multi_linestring_tag>
=======
#ifdef BOOST_GEOMETRY_DEBUG_RELATE_BOUNDARY_CHECKER
    Geometry const& geometry;
#endif
};

template <typename Geometry, typename WithinStrategy>
class boundary_checker<Geometry, WithinStrategy, multi_linestring_tag>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    typedef typename point_type<Geometry>::type point_type;

public:
<<<<<<< HEAD
=======
    typedef WithinStrategy equals_strategy_type;
    
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boundary_checker(Geometry const& g)
        : is_filled(false), geometry(g)
    {}

    // First call O(NlogN)
    // Each next call O(logN)
    template <boundary_query BoundaryQuery>
    bool is_endpoint_boundary(point_type const& pt) const
    {
<<<<<<< HEAD
=======
        typedef geometry::less<point_type, -1, typename WithinStrategy::cs_tag> less_type;

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        typedef typename boost::range_size<Geometry>::type size_type;
        size_type multi_count = boost::size(geometry);

        if ( multi_count < 1 )
            return false;

        if ( ! is_filled )
        {
            //boundary_points.clear();
            boundary_points.reserve(multi_count * 2);

            typedef typename boost::range_iterator<Geometry const>::type multi_iterator;
            for ( multi_iterator it = boost::begin(geometry) ;
                  it != boost::end(geometry) ; ++ it )
            {
                typename boost::range_reference<Geometry const>::type
                    ls = *it;

                // empty or point - no boundary
                if (boost::size(ls) < 2)
                {
                    continue;
                }

                typedef typename boost::range_reference
                    <
                        typename boost::range_value<Geometry const>::type const
                    >::type point_reference;

                point_reference front_pt = range::front(ls);
                point_reference back_pt = range::back(ls);

                // linear ring or point - no boundary
<<<<<<< HEAD
                if (! equals::equals_point_point(front_pt, back_pt))
=======
                if (! equals::equals_point_point(front_pt, back_pt, equals_strategy_type()))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                {
                    // do not add points containing NaN coordinates
                    // because they cannot be reasonably compared, e.g. with MSVC
                    // an assertion failure is reported in std::equal_range()
                    if (! geometry::has_nan_coordinate(front_pt))
                    {
                        boundary_points.push_back(front_pt);
                    }
                    if (! geometry::has_nan_coordinate(back_pt))
                    {
                        boundary_points.push_back(back_pt);
                    }
                }
            }

            std::sort(boundary_points.begin(),
                      boundary_points.end(),
<<<<<<< HEAD
                      geometry::less<point_type>());
=======
                      less_type());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

            is_filled = true;
        }

        std::size_t equal_points_count
            = boost::size(
                std::equal_range(boundary_points.begin(),
                                 boundary_points.end(),
                                 pt,
<<<<<<< HEAD
                                 geometry::less<point_type>())
=======
                                 less_type())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            );

        return equal_points_count % 2 != 0;// && equal_points_count > 0; // the number is odd and > 0
    }

private:
    mutable bool is_filled;
    // TODO: store references/pointers instead of points?
    mutable std::vector<point_type> boundary_points;

    Geometry const& geometry;
};

}} // namespace detail::relate
#endif // DOXYGEN_NO_DETAIL

}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_RELATE_BOUNDARY_CHECKER_HPP
